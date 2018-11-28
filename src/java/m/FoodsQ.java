package m;

import m.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodsQ {

  private static Logger logger = Logger.getLogger(FoodsQ.class.getName());
  private static Connection conn = new DbConn().getConnection();

  public static ArrayList<Food> selectAll() throws Exception {
    Connection C = new DbConn().getConnection();
    ArrayList<Food> mo = new ArrayList<>();
    String query = "SELECT * FROM foods";
    try (PreparedStatement PS = C.prepareStatement(query)) {
      ResultSet RS = PS.executeQuery();
      while (RS.next()) {
        mo.add(new Food(
                RS.getString("name"),
                RS.getInt("price"),
                RS.getString("picture_url"),
                RS.getString("description")
        ));
      }
    }
    return mo;
  }
  
  public static Boolean newFood(Food d) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement(
              "INSERT INTO foods(name, price, picture_url, description) VALUES(?,?,?,?)");
      statement.setString(1, d.getName());
      statement.setInt(2, d.getPrice());
      statement.setString(3, d.getPictureUrl());
      rowAffected = statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(FoodsQ.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowAffected != 0;
  }

  public static Food getWhere(String name) {
    Food f = null;
    try {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM foods WHERE name=?");
      statement.setString(1, name);
      ResultSet rs = statement.executeQuery();
      if (rs.first()) {
        f = new Food(rs.getString("name"), rs.getInt("price"), rs.getString("picture_url"), rs.getString("description"));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return f;
  }

  public static boolean update(String old_name, Food d) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement("UPDATE foods SET name=?, price=?, picture_url=?, description=? "
              + "WHERE name=?");
      statement.setString(1, d.getName());
      statement.setInt(2, d.getPrice());
      statement.setString(3, d.getPictureUrl());
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
      PreparedStatement statement = conn.prepareStatement("DELETE FROM foods WHERE name=? ");
      statement.setString(1, name);
      rowAffected = statement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return rowAffected != 0;
  }
}
