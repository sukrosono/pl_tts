<%-- 
    Document   : form_order
    Created on : Dec 1, 2018, 3:45:47 PM
    Author     : enter
--%>
<%-- any content can be specified here e.g.: --%>
<%@page import="java.util.ArrayList"%>
<%@page import="m.DrinksQ"%>
<%@page import="m.Drink"%>
<%@page import="m.FoodsQ"%>
<%@page import="m.Food"%>
<%@page import="m.Page" %>
<%@page pageEncoding="UTF-8"%>
<%
  Page webPage= new Page("Pesanan baru");
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
  
  
//  Food f = (Food) request.getAttribute("dataToEdit");
  ArrayList<Drink> drinks = DrinksQ.selectAll();
  ArrayList<Food> foods = FoodsQ.selectAll();
%>
<!DOCTYPE html>
<html>
  <jsp:include page="./header/head.title.jsp"/>
  <script type="text/javascript" src="<%= baseUrl %>/js/main.js">
  </script>
  <style>
    div.modal-body img {
      max-width: 400px;
      margin: 10px 10px;
    }
    div.card {
      top: 350px;
      margin: 10px 0px;
      padding: 10px 10px;
    }
    @media (min-width: 576px) {
      .modal-dialog { max-width: none; }
    }
    .modal-dialog {
      width: 98%;
      height: 92%;
      padding: 0;
    }
  </style>
  <body>
    <div class="container-fluid">
      <div class="row justify-content-center">
        <div class="col-sm-10 col-sm-offset-1">
          <h2>Pesanan pengunjung yang baru datang</h2>
          <form method="post" action="<%= baseUrl %>/pesanan"
                ><!--enctype="multipart/form-data"--> 
            <div class="form-group">
              <label>Nama pengunjung</label>
              <input type="text" name="name" required autocomplete="off" class="form-control"
                     >
              <!--value="<%//o.name %>"-->
            </div>
            <div class="form-group">
              <label>Nomor meja</label>
              <input type="number" name="table_number" required autocomplete="off" class="form-control">
              <!--value="<% //o.table_number %>"-->
              <!--onchange="on_change_listener()"-->
            </div>

            <input type="text" name="foods_order" 
                   value="<% //o.foods_order%>" hidden >
            <input type="text" name="drinks_order" 
                   value="<% //o.drinks_order%>" hidden>
            <input type="number" name="total_price" hidden 
                   readonly class="form-control">
            <!--value="<% //o.total_price%>"-->
            <br>
            <button type="button" class="btn btn-outline-primary btn-block" onclick="calc_payment()" disabled id="hitung">
              hitung pembayaran
            </button>
            <br>
            Gunakan java beans
            <%@include file="./display_drink.jsp" %>
            <%@include file="./display_food.jsp" %>
            <br>
            <input type="submit" value="Submit" class="btn btn-primary"
              disabled>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
