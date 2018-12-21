<%@page import="java.util.ArrayList"%>
<%@page import = "java.util.logging.Logger" %>
<%@page import = "java.util.logging.Level" %>
<%@page import="database.OrdersQ"%>
<%@page import="database.Order"%>
<%@page import="m.Page" %>
<%//@page import="m.Formater" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String baseUrl= request.getContextPath();
  
  
  Logger logger = Logger.getLogger(this.getClass().getName());
  Page webPage= new Page("Daftar pesanan pelanggan");
  Page.Web_Library jq = webPage.new Web_Library();
  jq.css_location = "";
  jq.js_location = baseUrl+"/node_modules/jquery/dist/jquery.min.js";
  webPage.libs.add(jq);
  Page.Web_Library jqUi = webPage.new Web_Library();
  jqUi.css_location = baseUrl+"/node_modules/jquery-ui-dist/jquery-ui.min.css";
  jqUi.js_location = baseUrl+"/node_modules/jquery-ui-dist/jquery-ui.min.js";
  webPage.libs.add(jqUi);
  Page.Web_Library bs = webPage.new Web_Library();
  bs.css_location = baseUrl+"/node_modules/bootstrap/dist/css/bootstrap.min.css";
  bs.js_location = baseUrl+"/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js";
  webPage.libs.add(bs);
  request.setAttribute("webPage", webPage);
  
  ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
  <jsp:include page="./header/head.title.jsp"/>
  <style>
    tr td img {
      max-width: 400px;
    }
  </style>
  <body>
    <h2>Daftar pesanan</h2>
    <div class="atas">
      <table class="list table">
        <thead class="thead-dark">
          <tr>
            <th>Id pesanan</th>
            <th>Name pelanggan</th>
            <th>Nomor meja</th>
            <th>Pesanan makanan</th>
            <th>Pesanan minuman</th>
            <th>Total pembayaran</th>
            <th>Aksi</th>
          </tr>
        </thead>
        <%
          if (!orders.isEmpty()) {
            for (Order o : orders) {
              out.print("<td>" + o.id_order + "</td>");
              out.print("<td>" + o.name + "</td>");
              out.print("<td>" + o.table_number + "</td>");
              out.print("<td>" + o.foods_order + "</td>");
              out.print("<td>" + o.drinks_order + "</td>");
              out.print("<td>" + o.total_price + "</td>");
              out.print("<td>");
//              proses bisnis tidak memungkinkan untuk mengedit pesanan!
//              out.print("<a href='"+ baseUrl+"/add/o/"+ dashedName+"'>edit</a> | ");
              out.print("<a href='"+ baseUrl+"/delete/order/"+ o.id_order+ "' class='btn btn-danger'>hapus</a>");
              out.print("</td>");
              out.print("</tr>");
            }
          } else {
            out.print("<tr>");
            out.print("<td colspan='7'>Data Tidak Ditemukan</td>");
            out.print("</tr>");
          }
        %>
      </table>
    </div>
  </body>
</html>