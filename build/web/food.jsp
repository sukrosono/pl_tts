<%-- any content can be specified here e.g.: --%>
<% @page import="m.Food"%>
<%@ page pageEncoding="UTF-8" %>
<%
  Matakuliah existingMk = (Matakuliah) request.getAttribute("dataToEdit");
  Food f = new F
%>
<div class="row">
  <div class="col-sm-10 col-sm-offset-1">
  <form method="post" action="<% out.print(request.getContextPath()); %>/edit">
    <input type="text" class="hidden" name="data" value="food">
    <div class="form-group">
      <label>Food name</label>
      <input type="text" name="name" required autocomplete="off" class="form-control"
             value="<%out.print(existingMk.getKode());%>" readonly="">
    </div>
    <div class="form-group">
      <label>Price</label>
      <input type="text" name="price" required autocomplete="off" class="form-control"
             value="<%out.print(existingMk.getNama());%>">
    </div>
    <div class="form-group">
      <label>Description</label>
      <input type="text" name="description" required autocomplete="off" class="form-control"
             value="<%out.print(existingMk.getNama());%>">
    </div>
    <div class="form-group">
      <label>Picture </label>
      <input type="text" name="picture" required autocomplete="off" class="form-control"
             value="<%out.print(existingMk.getJumlahSks());%>">
    </div>
    <input type="submit" value="Submit" class="btn btn-default">
  </form>
  </div>
</div>