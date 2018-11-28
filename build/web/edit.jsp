<%-- 
    Document   : edit
    Created on : Oct 20, 2018, 8:11:47 PM
    Author     : enter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="/T1_Assistant/css/bootstrap.min.css">
    <title>Editing ${data}</title>
  </head>
  <body>
    <h2 class="text-center">${data}</h2>
    <%
    if (request.getAttribute("data").equals("student")) {
    %>
      <%@include file="./mahasiswa.jspf" %>
    <%
      } else {
    %>
      <%@include file="./matakuliah.jspf" %>
    <%
      }
    %>
  </body>
</html>
