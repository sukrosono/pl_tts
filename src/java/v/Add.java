/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jdk.nashorn.internal.objects.NativeError;
import m.Drink;
import m.DrinksQ;
import m.Food;
import m.FoodsQ;
import m.Formater;

/**
 *
 * @author enter
 */
@WebServlet(name = "Add", urlPatterns = {"/add/*"})
@MultipartConfig
public class Add extends HttpServlet {

  Logger logger = Logger.getLogger(Data.class.getName());

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
    response.setContentType("text/html;charset=UTF-8");
    String[] requestedUrl = RequestHelper.parseReqUrl(request);
    try (PrintWriter out = response.getWriter()) {
      if (requestedUrl[0].equals("drink")) {
        Drink d = new Drink("");
        d.setDescription("");
        d.setPicture_url("");
        d.setPrice(0);
        if (requestedUrl.length > 1) {
//        add existing data
          String drinkId = Formater.normalize(requestedUrl[1]);
          logger.log(Level.SEVERE, "drinkID is " + drinkId);
          Drink existingDrink = DrinksQ.getWhere(drinkId);
          if (existingDrink != null) {
            d = existingDrink;
          }
        }
        request.setAttribute("dataToEdit", d);
        request.getRequestDispatcher("/view/form_drink.jsp").forward(request, response);
      }
      if (requestedUrl[0].equals("food")) {
        Food newFood = new Food("");
//        newFood.setPrice(0);
        newFood.setDescription("");
        newFood.setPictureUrl("");
        request.setAttribute("dataToEdit", newFood);
//        out.print("before include");
        request.getRequestDispatcher("/view/form_food.jsp").include(request, response);
//        out.print("i use include");
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
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    ArrayList<Drink> ds = DrinksQ.selectAll();
    ServletContext context = getServletContext();
    boolean inserted = false;
    OutputStream out = null;
    InputStream filecontent = null;
    String path = "/images/upload";
    path = getServletContext().getRealPath(path);
//    context.log("data: " + request.getParameter("data"));
    if ("drink".equals(request.getParameter("data"))) {
      try {
        Drink d = new Drink(request.getParameter("name"));
        d.setPrice(Integer.parseInt(request.getParameter("price")));
        final Part filePart = request.getPart("picture");
        final String fileName = getFileName(filePart);
        out = new FileOutputStream(new File(path + File.separator
                + fileName));
        filecontent = filePart.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) {
          out.write(bytes, 0, read);
        }
//        logger.log(Level.INFO, "New file " + fileName + " created at " + path);
//        logger.log(Level.INFO, "File{0}being uploaded to {1}",
//                new Object[]{fileName, path});
        d.setPicture_url(fileName);
        d.setDescription(request.getParameter("description"));
        boolean isOnDb = DrinksQ.getWhere(d.getName()) != null;
        if (isOnDb) {
          inserted = DrinksQ.update(d.getName(), d);
        } else {
          inserted = DrinksQ.newDrink(d);
        }
      } catch (Exception e) {
        context.log("fail insert d : ", e);
      } finally {
        if (inserted) {
          response.sendRedirect(request.getContextPath() + "/view/index_drinks.jsp");
        } else {
          response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "fail to save change");
        }
        if (out != null) {
          out.close();
        }
        if (filecontent != null) {
          filecontent.close();
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

  private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    logger.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
      if (content.trim().startsWith("filename")) {
        return content.substring(
                content.indexOf('=') + 1).trim().replace("\"", "");
      }
    }
    return null;
  }
}
