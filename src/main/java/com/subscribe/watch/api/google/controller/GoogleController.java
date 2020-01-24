package com.subscribe.watch.api.google.controller;

import com.subscribe.watch.api.config.session.SessionConstants;
import com.subscribe.watch.api.google.service.GoogleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class GoogleController {

  private HttpSession httpSession;
  private GoogleServiceImpl googleService;

  private static final String GMAIL_SEARCH_QUERY = "from:(googleplay)";

  public GoogleController(HttpSession httpSession, GoogleServiceImpl googleService) {
    this.httpSession = httpSession;
    this.googleService = googleService;
  }

  @GetMapping("/me")
  public Map<String, Object> me() throws GeneralSecurityException, IOException {
    Map<String, Object> response = new LinkedHashMap<>();
    String token = (String) httpSession.getAttribute(SessionConstants.USER_TOKEN);
    response.put("profile", httpSession.getAttribute(SessionConstants.LOGIN_USER));
    response.put("code", httpSession.getAttribute(SessionConstants.USER_CODE));
    response.put("token", token);

    //이메일 불러오는 시간 측정 start
    long start = System.currentTimeMillis();

     googleService.getGmailList(token);
    //googleService.listMessagesMatchingQuery(token, GMAIL_SEARCH_QUERY);

    // 이메일 불러오는 시간 측정 end
    long end = System.currentTimeMillis();
    System.out.println( "실행 시간 : " + ( end - start )/1000.0 );


    return response;
  }

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  public Principal userInfo(Principal principal) {
    return principal;
  }

}
