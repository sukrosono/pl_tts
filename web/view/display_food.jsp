<%-- 
    Document   : display_food
    Created on : Dec 1, 2018, 7:16:22 PM
    Author     : enter
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#food">
  Tampilkan daftar makanan
</button>
<div class="modal fade" id="food" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document"><div class="modal-content">
    <div class="modal-body">
      <% for (Food f : foods) { %>
      <div class="card">
        <img src="<% out.println(baseUrl+ "/images/upload/"+ f.getPictureUrl()); %>"
            class="card-img-top">
        </img>
        <div class="card-body">
          <h4 class="card-title">
            <%= f.getName() %>
          </h4>
          <p>
            Harga: 
            <%
              out.println(f.getPrice());
            %>
          </p>
          <p class="card-text">
            <%
              out.println(f.getDescription());
            %>
          </p>
          <input class="form-control" type="number">
          <button type="button" onclick="addFood()" class="btn btn-primary">
            tambahkan ke pesanan
          </button>
        </div>
      </div>
      <% } %>
    </div>
  </div></div>
</div>