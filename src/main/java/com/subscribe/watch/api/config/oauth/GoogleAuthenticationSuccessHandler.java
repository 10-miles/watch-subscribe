package com.subscribe.watch.api.config.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscribe.watch.api.config.session.SessionConstants;
import com.subscribe.watch.api.entity.GoogleUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private HttpSession httpSession;
  private ObjectMapper objectMapper;

  public GoogleAuthenticationSuccessHandler(HttpSession httpSession, ObjectMapper objectMapper) {
    this.httpSession = httpSession;
    this.objectMapper = objectMapper;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    System.out.println("onAuthenticationSuccess");
    Object details = authentication.getDetails();
    OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails)details;
    String token = oauthDetails.getTokenValue();

    httpSession.setAttribute(SessionConstants.LOGIN_USER, getGoogleUser(authentication));
    httpSession.setAttribute(SessionConstants.USER_CODE, request.getParameter("code"));
    httpSession.setAttribute(SessionConstants.USER_TOKEN, token);

    response.sendRedirect("/me");
  }

  private GoogleUser getGoogleUser(Authentication authentication) { // OAuth 인증정보를 통해 GoogleUser 인스턴스 생성
    OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
    return objectMapper.convertValue(oAuth2Authentication.getUserAuthentication().getDetails(), GoogleUser.class);
  }

}
