<%@ page import ="java.sql.*" %>
 
<%
    String userid = request.getParameter("username");    
    String pwd = request.getParameter("password");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = new DbConn().getConnection();
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from users where username='" + userid + "' and password='" + pwd + "'");
    if (rs.next()) {
        session.setAttribute("userid", userid);
        out.println("welcome " + userid);
        out.println("<a href='logout.jsp'>Log out</a>");
        response.sendRedirect("view/home.jsp");
    } else {
        out.println("Password Anda Salah!!! Silahkan ulangi. <a href='index.jsp'>Try Again</a>");
    }
%>