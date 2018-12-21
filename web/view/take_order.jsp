<%-- 
    Document   : take_order
    Created on : Dec 2, 2018, 2:34:02 PM
    Author     : enter
--%>

<%@page import="m.Page" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Page webPage= new Page("Sistem pemesanan");
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
  <!--unused-->
  <body>
    <div class="container-fluid">
      
      
    </div>
  </body>
</html>
