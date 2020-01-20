package com.subscribe.watch.api.controller;

import com.subscribe.watch.api.config.oauth.ClientResources;
import com.subscribe.watch.api.config.session.SessionConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class googleController {

  private HttpSession httpSession;
  private ClientResources google;

  public googleController(HttpSession httpSession, ClientResources google) {
    this.httpSession = httpSession;
    this.google = google;
  }

  @GetMapping("/me")
  public Map<String, Object> me() {

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("profile", httpSession.getAttribute(SessionConstants.LOGIN_USER));
    response.put("code", httpSession.getAttribute(SessionConstants.USER_CODE));
    response.put("token", httpSession.getAttribute(SessionConstants.USER_TOKEN));

    return response;
  }

//  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
//  public Principal home(Principal principal) {
//    Object details = ((OAuth2Authentication) principal).getDetails();
//    System.out.println(details);
//    OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails)details;
//    String token = oauthDetails.getTokenValue();
//    System.out.println(token);
//    return principal;
//  }


}
