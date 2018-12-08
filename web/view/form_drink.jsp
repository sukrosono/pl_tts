<%-- any content can be specified here e.g.: --%>
<%@ page import="m.Drink"%>
<%@ page pageEncoding="UTF-8" %>
<%
  Drink d = (Drink) request.getAttribute("dataToEdit");
  String baseUrl= request.getContextPath();
  String h2 = "";
  if (d.getName().equals("")) {
    h2= "new data";
  } else {
    h2= "editing data";
  }
%>
<h2><%= h2 %></h2>
<div class="row">
  <div class="col-sm-10 col-sm-offset-1">
    <form method="post" action="<% out.print(baseUrl); %>/add"
          enctype="multipart/form-data" >
      <input type="text" class="hidden" name="data" value="drink">
      <div class="form-group">
        <label>Drink name</label>
        <input type="text" name="name" required class="form-control"
               value="<%= d.getName() %>" >
      </div>
      <div class="form-group">
        <label>Price</label>
        <input type="number" name="price" required class="form-control"
               value="<%= d.getPrice() %>">
      </div>
      <div class="form-group">
        <label>Description</label>
        <input type="text" name="description" required autocomplete="off" class="form-control"
               value="<%= d.getDescription() %>">
      </div>
      <div class="form-group">
        <label>Picture </label>
        <input type="file" name="picture" required autocomplete="off" class="form-control"
               value="<%= d.getPicture_url() %>">
      </div>
      <input type="submit" value="submit" class="btn btn-default">
    </form>
  </div>
  <div>
    <!--<p><% //note %></p>-->
  </div>
</div>