<%-- any content can be specified here e.g.: --%>
<%@page import="m.Food"%>
<%@page import="m.Page" %>
<%@page pageEncoding="UTF-8"%>
<%
  Page webPage= new Page("Input makanan");
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
  Food f = (Food) request.getAttribute("dataToEdit");
  String h2 = "";
  if (f.getName() == "") {
    h2= "new data";
  } else {
    h2= "editing data";
  }
%>
<!DOCTYPE html>
<html>
  <jsp:include page="./header/head.title.jsp"/>
  <body>
    <div class="container-fluid"><div class="row justify-content-center">
      <div class="col-sm-10 col-sm-offset-1">
        <h2><%= h2 %></h2>
        <form method="post" action="<% out.print(baseUrl); %>/add"
              enctype="multipart/form-data">
          <input type="text" hidden name="data" value="food">
          <div class="form-group">
            <label>Food name</label>
            <input type="text" name="name" required autocomplete="off" class="form-control"
                   value="<%=f.getName() %>" >
          </div>
          <div class="form-group">
            <label>Price</label>
            <input type="number" name="price" required autocomplete="off" class="form-control"
                   value="<%= f.getPrice()%>">
          </div>
          <div class="form-group">
            <label>Description</label>
            <input type="text" name="description" required autocomplete="off" class="form-control"
                   value="<%= f.getDescription()%>">
          </div>
          <div class="form-group">
            <label>Picture </label>
            <input type="file" name="picture" required autocomplete="off" class="form-control"
                   value="<%= f.getPictureUrl()%>">
          </div>
          <input type="submit" value="Submit" class="btn btn-primary">
        </form>
      </div>
    </div></div>
  </body>
</html>