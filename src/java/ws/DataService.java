/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import m.Drink;
import m.DrinksQ;
import m.Food;
import m.FoodsQ;

/**
 *
 * @author enter
 */
//implementasi penggunaan WS, biar dpet bonus
// fungsinya untuk mengakses data food dan drink
@WebService(serviceName = "DataService")
//, endpointInterface="ws.DataService"
//,  targetNamespace = "http://pl_tts/wsdl"
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class DataService {
  Logger logger = Logger.getGlobal();
  
  public DataService() {
  }
  
  /**
   * Web service operation
   * @return string
   */
  @WebMethod
  public String say() {
//    logger.log(Level.SEVERE, "say");
//    System.out.println("say");
    return "say";
  }
  
}
