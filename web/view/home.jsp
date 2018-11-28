<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>       
        <%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Anda Belum login <br/>
<a href="index.jsp">Silahkan Login</a>
<%} else {
%>
<h1>Selamat Datang di PL <%=session.getAttribute("userid")%></h1><br>
Masukan pilihan Anda!<br>
<br>
<%
    out.print("<a href=''>Lihat Data</a>");
    %>
           
            <br>
            <a href="">Tambah Data</a>
            <br>
            <a href="../logout.jsp">Logout</a>
<%
    }
%>
</body>
</html>

