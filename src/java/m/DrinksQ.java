/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enter
 */
public class DrinksQ {

  private static Logger logger = Logger.getLogger(DrinksQ.class.getName());
  private static Connection conn = new DbConn().getConnection();

  public static ArrayList<Drink> selectAll() {
    ArrayList<Drink> data = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
      statement = conn.createStatement();
      rs = statement.executeQuery("SELECT * FROM drinks");
      data = new ArrayList<Drink>();
      while (rs.next()) {
        Drink d = new Drink(rs.getString("name"), rs.getInt("price"), rs.getString("picture_url"));
        d.setDescription(rs.getString("description"));
        data.add(d);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "DrinksQ.selectAll() " + e.toString());
      logger.log(Level.SEVERE, "DrinksQ.selectAll() " + e.getMessage());
    }
    return data;
  }

  public static Boolean newDrink(Drink d) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement(
              "INSERT INTO drinks(name, price, picture_url, description) VALUES(?,?,?,?)");
      statement.setString(1, d.getName());
      statement.setInt(2, d.getPrice());
      statement.setString(3, d.getPicture_url());
      statement.setString(4, d.getDescription());
      rowAffected = statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(DrinksQ.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowAffected != 0;
  }

  public static Drink getWhere(String name) {
    Drink d = null;
    try {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM drinks WHERE name=?");
      statement.setString(1, name);
      ResultSet rs = statement.executeQuery();
      if (rs.first()) {
        d = new Drink(rs.getString("name"), rs.getInt("price"), rs.getString("picture_url"));
        d.setDescription(rs.getString("description"));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return d;
  }

  public static boolean update(String old_name, Drink d) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement("UPDATE drinks SET name=?, price=?, picture_url=?, description=? "
              + "WHERE name=?");
      statement.setString(1, d.getName());
      statement.setInt(2, d.getPrice());
      statement.setString(3, d.getPicture_url());
      statement.setString(4, d.getDescription());
      statement.setString(5, old_name);
      rowAffected = statement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return rowAffected != 0;
  }

  public static boolean delete(String name) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement("DELETE FROM drinks WHERE name=? ");
      statement.setString(1, name);
      rowAffected = statement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return rowAffected != 0;
  }
}
