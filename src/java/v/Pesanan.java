/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import database.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import m.Drink;
import m.DrinksQ;
import m.Food;
import m.FoodsQ;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import session_beans.TAS_Session_beans;

/**
 *
 *
 * @author enter
 */
@WebServlet(name = "Pesanan", urlPatterns = {"/pesanan/*"})
public class Pesanan extends HttpServlet {

  @EJB
  private TAS_Session_beans tAS_Session_beans;

  Logger logger = Logger.getLogger(Pesanan.class.getName());

  public Pesanan() {
    logger.log(Level.SEVERE, "constructor11");
//    try {
//      Context ic = new InitialContext();
//      Object tAS_Session_beans = ic.lookup("TAS_Session_beans");
//    } catch (NamingException ex) {
//      Logger.getLogger(Pesanan.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }


  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
//      hit bonus, menggunakan EJB
      ArrayList<Order> orders = tAS_Session_beans.getsOrder();
      if (orders != null) {
        request.setAttribute("orders", orders);
//        with include js and css location is broken
        request.getRequestDispatcher("/view/index_orders.jsp").include(request, response);
      } else {
        out.write("cannot access db");
      }
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    System.out.println("accepting get req");
    RequestHelper.parseReqUrl(request);
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String[] url = RequestHelper.parseReqUrl(request);
    String foods_order = request.getParameter("foods_order");
    String drinks_order = request.getParameter("drinks_order");
//    logger.log(Level.SEVERE, "fo " + foods_order);
//    logger.log(Level.SEVERE, "d " + drinks_order);
//    ordinary post
    if (url == null) {
      Order o = new Order(request.getParameter("table_number"), request.getParameter("name"));
      logger.log(Level.SEVERE, "total_price: " + request.getParameter("total_price"));
      if (String.valueOf(request.getParameter("total_price")).equals("null")) {
//        debug with dump whole post data?
        o.total_price = 0L;

      } else {
        o.total_price = Long.valueOf(request.getParameter("total_price"));
      }
      o.foods_order = request.getParameter("foods_order");
      o.drinks_order = request.getParameter("drinks_order");
      if (tAS_Session_beans.insert_new_order(o)) {
        response.sendRedirect(request.getContextPath() + "/pesanan");
      } else {
        response.getWriter().println("terjadi kesalahan, pesanan tidak dapat disimpan");
      }
    } else {
      if (url[0].equals("calc_payment")) {
        Long total_price = calc_payment(foods_order, drinks_order);
        JSONObject json = new JSONObject();
        json.put("total_price", total_price);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json.toJSONString());
      }
    }
  }

  private Long calc_payment(String foods_order_str, String drinks_order_str) {
    Long r = 0L;
    try {
      JSONParser p = new JSONParser();
      JSONArray foods_order = (JSONArray) p.parse(foods_order_str);
      JSONArray drinks_order = (JSONArray) p.parse(drinks_order_str);
      if (!drinks_order.isEmpty()) {
        for (int i = 0; i < drinks_order.size(); i++) {
          JSONObject drink_order = (JSONObject) drinks_order.get(i);
          int single_price = 0;
          Drink d = DrinksQ.getWhere(drink_order.get("name").toString());
          if (d != null) {
            single_price = d.getPrice();
          }
          int qty = Integer.parseInt(drink_order.get("qty").toString());
          r += new Long(single_price * qty);
        }
      }
      if (!foods_order.isEmpty()) {
        for (int i = 0; i < foods_order.size(); i++) {
          JSONObject food_order = (JSONObject) foods_order.get(i);
          int single_price = 0;
          Food f = FoodsQ.getWhere(food_order.get("name").toString());
          if (f != null) {
            single_price = f.getPrice();
          }
          int qty = Integer.parseInt(food_order.get("qty").toString());
          r += new Long(single_price * qty);
        }
      }
    } catch (ParseException ex) {
      logger.log(Level.SEVERE, null, ex);
    }
    return r;
  }
}
