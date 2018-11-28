package m;

public class Food {

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
  private String pictureUrl;
  private String description;

  public Food(String name) {
    this.name = name;
  }

  public Food(String name, int price, String pictureUrl, String desc) {
    this.name = name;
    this.price = price;
    this.pictureUrl = pictureUrl;
    this.description = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }
}
