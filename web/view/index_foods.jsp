<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="m.FoodsQ"%>
<%@page import="m.Food"%>
<%@page import="m.Formater" %>
<%@page import="m.Page" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Page webPage= new Page("Daftar Makanan");
  Page.Web_Library jq = webPage.new Web_Library();
  jq.css_location = "";
  jq.js_location = "../node_modules/jquery/dist/jquery.min.js";
  webPage.libs.add(jq);
  Page.Web_Library jqUi = webPage.new Web_Library();
  jqUi.css_location = "../node_modules/jquery-ui-dist/jquery-ui.min.css";
  jqUi.js_location = "../node_modules/jquery-ui-dist/jquery-ui.min.js";
  webPage.libs.add(jqUi);
  Page.Web_Library bs = webPage.new Web_Library();
  bs.css_location = "../node_modules/bootstrap/dist/css/bootstrap.min.css";
  bs.js_location = "../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js";
  webPage.libs.add(bs);
  request.setAttribute("webPage", webPage);
  String baseUrl= request.getContextPath();
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
    <%
      try {
        ArrayList<Food> food = FoodsQ.selectAll();
    %>
    <h2>Daftar makanan</h2>
    <div class="atas">
      <table class="list table">
        <thead class="thead-light">
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Deskripsi</th>
            <th>Gambar</th>
            <th>Aksi</th>
          </tr>
        </thead>
        <%
          if (!food.isEmpty()) {
            for (Food f : food) {
              String dashedName= Formater.toDashCase(f.getName());
              out.print("<td>" + f.getName() + "</td>");
              out.print("<td>" + f.getPrice() + "</td>");
              out.print("<td>" + f.getDescription() + "</td>");
              out.print("<td><img src='" + 
                      baseUrl+ "/images/upload/"+
                      f.getPictureUrl() + "'/></td>");
              out.print("<td>");
              out.print("<a href='"+ baseUrl+"/add/food/"+ dashedName+"'>edit</a> | ");
              out.print("<a href='"+ baseUrl+"/delete/food/"+ dashedName+ "' class='btn btn-danger'>hapus</a>");
              out.print("</td>");
              out.print("</tr>");
            }
          } else {
            out.print("<tr>");
            out.print("<td colspan='6'>Data Tidak Ditemukan</td>");
            out.print("</tr>");
          }
        %>
      </table>
    </div>
    <%
      } catch (Exception E) {
        out.print(E.getMessage());
      }
    %>
  </body>
</html>