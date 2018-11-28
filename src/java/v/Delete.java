/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import m.DrinksQ;
import m.FoodsQ;

/**
 *
 * @author enter
 */
@WebServlet(name = "Delete", urlPatterns = {"/delete/*"})
public class Delete extends HttpServlet {

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
    String[] requestedUrl = request.getPathInfo().split("/");
    if (requestedUrl[1].equals("drink")) {
      if (DrinksQ.delete(requestedUrl[2])) {
        response.sendRedirect(request.getContextPath());
      } else {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unable delete "+ requestedUrl[2]);
      }
    }
    if (requestedUrl[1].equals("food")) {
      if (FoodsQ.delete(requestedUrl[2])) {
        response.sendRedirect(request.getContextPath()+"/#tabs-2");
      } else {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unable delete "+ requestedUrl[2]);
      }
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
//  @Override
//  protected void doPost(HttpServletRequest request, HttpServletResponse response)
//          throws ServletException, IOException {
//    processRequest(request, response);
//  }

}
