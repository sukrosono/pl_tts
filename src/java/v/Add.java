/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import m.Drink;
 import m.DrinksQ;
 import m.Food;
 import m.FoodsQ;

/**
 *
 * @author enter
 */
@WebServlet(name = "add", urlPatterns = {"/add"})
public class Add extends HttpServlet {

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
    response.sendRedirect(request.getContextPath());
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
    ServletContext context = getServletContext();
    boolean inserted = false;
//    context.log("data: " + request.getParameter("data"));
    if ("drink".equals(request.getParameter("data"))) {
       try {
         Drink d = new Drink(request.getParameter("name"));
         d.setPrice(Integer.parseInt(request.getParameter("price")));
         d.setPicture_url(request.getParameter("picture_url"));
         d.setDescription(request.getParameter("description"));
         inserted = DrinksQ.newDrink(d);
       } catch (Exception e) {
         context.log("fail insert d : ", e);
       } finally {
         if (inserted) {
           response.sendRedirect(request.getContextPath());
         } else {
           response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "fail to insert new student");
         }
       }
    }
    if ("food".equals(request.getParameter("data"))) {
       try {
         Food f = new Food(request.getParameter("name"));
         f.setPrice(Integer.parseInt(request.getParameter("price")));
         f.setPictureUrl(request.getParameter("picture_url"));
         f.setDescription(request.getParameter("description"));
         inserted = FoodsQ.newFood(f);
       } catch (NumberFormatException e) {
         context.log("fail insert mk : ", e);
       } finally {
         if (inserted) {
           response.sendRedirect(request.getContextPath());
         } else {
           response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "fail to insert new student");
         }
       }
    }
  }

}
