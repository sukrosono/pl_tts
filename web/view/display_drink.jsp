<%-- 
    Document   : display_drink
    Created on : Dec 1, 2018, 7:16:22 PM
    Author     : enter
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#drink">
  Tampilkan daftar minuman
</button>
<div class="modal fade" id="drink" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document"><div class="modal-content">
    <div class="modal-body">
      <% for (Drink drink : drinks) { %>
      <div class="card">
        <img src="<% out.println(baseUrl+ "/images/upload/"+ drink.getPicture_url()); %>"
             class="card-img-top">
          </img>
        <div class="card-body">
          <h4 class="card-title">
            <%= drink.getName() %>
          </h4>
          <p>
            Harga: 
            <%
              out.println(drink.getPrice());
            %>
          </p>
          <p class="card-text">
            <%
              out.println(drink.getDescription());
            %>
          </p>
          <input class="form-control" type="number">
          <button type="button" onclick="addDrink()" class="btn btn-primary">
            tambahkan ke pesanan
          </button>
        </div>
      </div>
      <% } %>
      </div>
  </div></div>
</div>