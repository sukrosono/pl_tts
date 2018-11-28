<%-- any content can be specified here e.g.: --%>
<%@ page import="m.Drink"%>
<%@ page pageEncoding="UTF-8" %>
<%
  Drink d = (Drink) request.getAttribute("dataToEdit");
//  Mahasiswa existingMhs = (Mahasiswa) request.getAttribute("dataToEdit");
//  Drink d1= new Dr
%>
<div class="row">
  <div class="col-sm-10 col-sm-offset-1">
  <form method="post" action="<% out.print(request.getContextPath()); %>/edit">
    <input type="text" class="hidden" name="data" value="drink">
    <div class="form-group">
      <label>Drink name</label>
      <input type="text" name="name" required class="form-control"
             value="<% out.print(new D); %>" readonly="">
    </div>
    <div class="form-group">
      <label>Price</label>
      <input type="text" name="price" required class="form-control"
             value="<% out.print(existingMhs.getNama()); %>">
    </div>
    <div class="form-group">
      <label>Description</label>
      <input type="file" name="description" required autocomplete="off" class="form-control"
             value="<% out.print(existingMhs.getTanggalLahir()); %>">
    </div>
    <div class="form-group">
      <label>Picture </label>
      <input type="file" name="picture" required autocomplete="off" class="form-control"
             value="<% out.print(existingMhs.getTanggalLahir()); %>">
    </div>
    <input type="submit" value="submit" class="btn btn-default">
  </form>
  </div>
</div>