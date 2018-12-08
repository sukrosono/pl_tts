/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m;

/**
 *
 * @author enter
 */
public class Formater {
  /**
   * normalize dash-case-string
   * @return 
   */
  public static String normalize(String x) {
    return x.replace("-", " ");
  }
  
  /**
   * replace space with - , to be used on url
   * @param stringWithSpace
   * @return 
   */
  public static String toDashCase(String stringWithSpace) {
    return stringWithSpace.replace(" ", "-");
  }
}
