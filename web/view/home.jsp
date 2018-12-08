<%@page import="m.Page" %>

<html>
  <%
    Page webPage= new Page("home");
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
  %>
  <jsp:include page="./header/head.title.jsp"/>
  <body>       
    <%
      if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
    %>
    Anda Belum login <br/>
    <a href="../index.jsp">Silahkan Login</a>
    <%} else {
    %>
    <h1>Selamat Datang di PL <%=session.getAttribute("userid")%></h1>
    <nav>
      <ul class="nav justify-content-center">
        <li class="nav-item">
          <a  class="nav-link active" href="./home.jsp">Home</a></li>
        <li class="nav-item">
          <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Tambah Data
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="../add/food">Makanan</a>
              <a class="dropdown-item" href="../add/drink">Minuman</a>
            </div>
          </div>
        </li>
        <li class="nav-item"><div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Lihat Data
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="./index_foods.jsp">Makanan</a>
              <a class="dropdown-item" href="./index_drinks.jsp">Minuman</a>
            </div>
          </div>
        </li>
        <li class="nav-item"><a class="nav-link" href="../logout.jsp">Logout</a></li>
      </ul>
    </nav>
    <br>
    <h2>Admin area</h2><br>
    <br>
    <br>

    <%
        }
    %>
  </body>
</html>

