/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author enter
 */
public class RequestHelper {

  private static Logger logger = Logger.getLogger(RequestHelper.class.getName());

  /**
   * parsing requested url separated by / in addition parsed url stored on
   * request so you can use it latter on view with key 'requestedUrl'
   *
   * @param request
   * @return parsed url
   */
  public static String[] parseReqUrl(HttpServletRequest request) {
    String[] requestedUrl = null;
    if (request != null) {
      if (!String.valueOf(request.getPathInfo()).equals("null")) {
        requestedUrl = request.getPathInfo().split("/");
        requestedUrl = Arrays.stream(requestedUrl).filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        request.setAttribute("requestedUrl", requestedUrl);
      } else {
        logger.log(Level.SEVERE, "you miss, pathinfo return null");
      }
    } else {
      logger.log(Level.SEVERE, "HELL how it could be req is null");
    }
    return requestedUrl;
  }
}
