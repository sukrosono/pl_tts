<%@ page import ="java.sql.*" %>
<%
    String user = request.getParameter("username");    
    String pwd = request.getParameter("password");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = new DbConn().getConnection();
    Statement st = con.createStatement();
    //ResultSet rs;         
    int i = st.executeUpdate("insert into users( username, password) values ('" + user + "','" + pwd + "')");
    if (i > 0) {
        //session.setAttribute("userid", user);
        response.sendRedirect("index.jsp");
       // out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
    } else { 
        response.sendRedirect("index.jsp");
    }
 %>