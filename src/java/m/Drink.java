package m;

/**
 * no encapsulation
 *
 * @author enter
 */
public class Drink {

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the price
   */
  public int getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * @return the picture_url
   */
  public String getPicture_url() {
    return picture_url;
  }

  /**
   * @param picture_url the picture_url to set
   */
  public void setPicture_url(String picture_url) {
    this.picture_url = picture_url;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  private String name;
  private int price;
  private String picture_url;
  private String description;

  public Drink(String name) {
    this.name = name;
  }

  public Drink(String name, int price, String picture_url) {
    this.name = name;
    this.price = price;
    this.picture_url = picture_url;
  }
}
