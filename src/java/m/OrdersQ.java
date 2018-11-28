package m;

import m.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersQ {

  private static Logger logger = Logger.getLogger(OrdersQ.class.getName());
  private static Connection conn = new DbConn().getConnection();

  public static ArrayList<Order> selectAll() throws Exception {
    Connection C = new DbConn().getConnection();
    ArrayList<Order> mo = new ArrayList<>();
    String query = "SELECT * FROM orders";
    try (PreparedStatement PS = C.prepareStatement(query)) {
      ResultSet RS = PS.executeQuery();
      while (RS.next()) {
        Order o = new Order(
                RS.getString("name")
        );
        o.setTable_number(RS.getString("table_number"));
        o.setId_order(RS.getInt("id_order"));
        o.setFoods_order(RS.getString("foods_order"));
        o.setDrinks_order(RS.getString("drinks_order"));
        o.setTotal_price(RS.getLong("total_price"));
        mo.add(o);
      }
    }
    return mo;
  }

  public static Boolean newOrder(Order o) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement(
              "INSERT INTO orders(id_order, table_number, name, foods_order, drinks_order, total_price)"
              + "VALUES(?,?,?,?,?,?)");
      statement.setInt(1, o.getId_order());
      statement.setString(2, o.getTable_number());
      statement.setString(3, o.getName());
      statement.setString(4, o.getFoods_order());
      statement.setString(5, o.getDrinks_order());
      statement.setLong(6, o.getTotal_price());
      rowAffected = statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(OrdersQ.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowAffected != 0;
  }

  public static Order getWhere(String id) {
    Order o = null;
    try {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders WHERE id_order=?");
      statement.setString(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.first()) {
        o = new Order(rs.getString("name"));
        o.setTable_number(rs.getString("table_number"));
        o.setFoods_order(rs.getString("foods_order"));
        o.setDrinks_order(rs.getString("drinks_order"));
        o.setTotal_price(rs.getLong("total_price"));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return o;
  }

  public static boolean update(String id, Order o) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement("UPDATE orders SET table_number=?, name=?, foods_order=?, drinks_order=?, total_price=?"
              + "WHERE id_order=?");
      statement.setString(1, o.getTable_number());
      statement.setString(2, o.getName());
      statement.setString(3, o.getFoods_order());
      statement.setString(4, o.getDrinks_order());
      statement.setLong(5, o.getTotal_price());
      statement.setString(6, id);
      rowAffected = statement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return rowAffected != 0;
  }

  public static boolean delete(String id) {
    int rowAffected = 0;
    try {
      PreparedStatement statement = conn.prepareStatement("DELETE FROM orders WHERE id_order=? ");
      statement.setString(1, id);
      rowAffected = statement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.toString());
    }
    return rowAffected != 0;
  }
}
