/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v;

import java.util.Arrays;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author enter
 */
public class RequestHelper {
  
  /**
   * parsing requested url separated by /
   * in addition parsed url stored on request so you can use it latter on view
   * with key 'requestedUrl'
   * @param request
   * @return parsed url
   */
  public static String[] parseReqUrl(HttpServletRequest request) {
    String[] requestedUrl = request.getPathInfo().split("/");
    requestedUrl= Arrays.stream(requestedUrl).filter(s -> !s.isEmpty())
      .toArray(String[]::new);
    request.setAttribute("requestedUrl", requestedUrl);
    return requestedUrl;
  }
}
