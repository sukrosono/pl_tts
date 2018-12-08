<%-- 
    Document   : head.title
    Created on : Dec 1, 2018, 8:24:50 AM
    Author     : enter
--%>
<%@ page import="m.Page" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%
    Page webPage= (Page) request.getAttribute("webPage");
    for (Page.Web_Library lib : webPage.libs) {
      out.println("<link rel='stylesheet' href=\'"+ lib.css_location +"\'>");
      out.println("<script src=\'"+ lib.js_location + "\'></script>");
    }
  %>
  <title>
    <%out.print(webPage.title); %>
  </title>
</head>
