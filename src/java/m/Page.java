/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m;

import java.util.ArrayList;

/**
 *
 * @author enter
 */
public class Page {

  public String title;
  public ArrayList<Web_Library> libs= new ArrayList<>();
  public Page() {
  }

  public Page(String title) {
    this.title = title;
  }
  
  public class Web_Library {
    public String js_location;
    public String css_location;
  }
}
