/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "edit", urlPatterns = {"/edit/*"})
public class Edit extends HttpServlet {

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
    ServletContext context = getServletContext();
    String[] requestedUrl = request.getPathInfo().split("/");
    String data = requestedUrl[1];
    request.setAttribute("data", data);
    if (data.equals("drink")) {
      Drink d = DrinksQ.getWhere(requestedUrl[2]);
      request.setAttribute("dataToEdit", d);
    }
    if (data.equals("food")) {
      Food f = FoodsQ.getWhere(requestedUrl[2]);
      request.setAttribute("dataToEdit", f);
    }
    request.getRequestDispatcher("/edit.jsp").include(request, response);
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
    if (request.getParameter("data").equals("drink")) {
      try {
        Drink d = new Drink(request.getParameter("name"));
        d.setPrice(Integer.parseInt(request.getParameter("price")));
        d.setPicture_url(request.getParameter("picture_url"));
        d.setDescription(request.getParameter("description"));
        if (DrinksQ.update(request.getParameter("name"), d)) {
          response.sendRedirect(request.getContextPath());
        } else {
          response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
      } catch (IOException | NumberFormatException ex) {
        Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (request.getParameter("data").equals("food")) {
      try {
        Food f = new Food(request.getParameter("name"));
        f.setPrice(Integer.parseInt(request.getParameter("price")));
        f.setPictureUrl(request.getParameter("pictureUrl"));
        if (FoodsQ.update(request.getParameter("name"), f)) {
          response.sendRedirect(request.getContextPath() + "/#tabs-2");
        } else {
          response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
      } catch (Exception e) {
        Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, e);
      }
    }
  }

}
