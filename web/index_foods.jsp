<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="ModelQuery.MakananQuery"%>
<%@page import="Model.Makanan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Makanan</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  <body>
    <%
        try {
            ArrayList<Makanan> makan = MakananQuery.select();
    %>
    <h2>Table Data</h2>
    <div class="atas">
      <table class="list">
        <tr>
          <th>Name</th>
          <th>Price</th>
          <th>Gambar</th>
        </tr>
        <%
            if (!makan.isEmpty()) {
                for (Makanan food : makan) {
                    out.print("<td>" + food.getName() + "</td>");
                    out.print("<td>" + food.getPrice() + "</td>");
                    out.print("<td>" + food.getPictureUrl() + "</td>");
                    out.print("</td>");
                    out.print("</tr>");
                }
            } else {
                out.print("<tr>");
                out.print("<td colspan='5'>Data Tidak Ditemukan</td>");
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