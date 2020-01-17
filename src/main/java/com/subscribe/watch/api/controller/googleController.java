package com.subscribe.watch.api.controller;

import com.subscribe.watch.api.config.oauth.SessionConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class googleController {

  private HttpSession httpSession;

  public googleController(HttpSession httpSession) {
    this.httpSession = httpSession;
  }

  @GetMapping("/me")
  public Map<String, Object> me(){
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("profile", httpSession.getAttribute(SessionConstants.LOGIN_USER));
    return response;
  }

  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  public Principal home(Principal principal) {
    return principal;
  }

  @RequestMapping(value = { "/tokenValue"}, method = RequestMethod.GET)
  public Map<String, String> user(Principal principal) {
//    Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    Object details = ((OAuth2Authentication) principal).getDetails();
    System.out.println(details);
//    Map<String, String> map = new LinkedHashMap<>();
//    map.put("tokenValue", (String) details.get("tokenValue"));
//    return map;
    return null;
  }

}
