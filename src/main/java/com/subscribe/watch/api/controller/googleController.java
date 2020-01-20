package com.subscribe.watch.api.controller;

import com.subscribe.watch.api.config.oauth.ClientResources;
import com.subscribe.watch.api.config.session.SessionConstants;
import org.springframework.http.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import java.security.Principal;
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

    return response;
  }

  @GetMapping("/accesstoken")
  public Map<String, Object> accesstoken() {
    Map<String, Object> response = new LinkedHashMap<>();
    String code = httpSession.getAttribute(SessionConstants.USER_CODE).toString();

    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
    parameters.add("code", code);
    parameters.add("client_id", google.getClient().getClientId());
    parameters.add("client_secret", google.getClient().getClientSecret());
    parameters.add("redirect_uri", "http://localhost:8080/login");
    parameters.add("grant_type", "authorization_code");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
    ResponseEntity<Map> responseEntity = restTemplate.exchange("https://oauth2.googleapis.com/token", HttpMethod.POST, requestEntity, Map.class);
//    ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, requestEntity, Map.class);
    Map<String, Object> responseMap = responseEntity.getBody();

    return responseMap;
  }

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  public String home(Principal principal) {
    Object details = ((OAuth2Authentication) principal).getDetails();
    System.out.println(details);
    OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails)details;
    String token = oauthDetails.getTokenValue();
    System.out.println(token);
    return token;
  }


}
