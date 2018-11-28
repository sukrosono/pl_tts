package m;

public class Order {
  private int id_order;
  private String table_number;
  private String name;
  private String foods_order;
  private String drinks_order;
  private long total_price;

  public Order(String person_name) {
    this.name= person_name;
  }

  public int getId_order() {
    return id_order;
  }

  public void setId_order(int id_order) {
    this.id_order = id_order;
  }

  public String getTable_number() {
    return table_number;
  }

  public void setTable_number(String table_number) {
    this.table_number = table_number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFoods_order() {
    return foods_order;
  }

  public void setFoods_order(String foods_order) {
    this.foods_order = foods_order;
  }

  public String getDrinks_order() {
    return drinks_order;
  }

  public void setDrinks_order(String drinks_order) {
    this.drinks_order = drinks_order;
  }

  public long getTotal_price() {
    return total_price;
  }

  public void setTotal_price(long total_price) {
    this.total_price = total_price;
  }
  
  
}
