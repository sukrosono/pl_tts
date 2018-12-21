package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class DbConn {

  private final String dbDriverClass = "com.mysql.jdbc.Driver";
  private final String dbUsername = "root";
  private final String dbPassword = "coldheart";
  private final String dbUrl = "jdbc:mysql://localhost:3306/pl_tss?zeroDateTimeBehavior=convertToNull";
  private static Connection connection = null;

  public Connection getConnection() {
    try {
      Class.forName(dbDriverClass);
      connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
      try {
        throw ex;
      } catch (SQLException ex1) {
        Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
    return connection;
  }
}
