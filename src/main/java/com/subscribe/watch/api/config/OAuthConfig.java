package com.subscribe.watch.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.servlet.Filter;

@Configuration
@EnableOAuth2Client
public class OAuthConfig {

  private final OAuth2ClientContext oauth2ClientContext;

  public OAuthConfig(@Qualifier ( "oauth2ClientContext") OAuth2ClientContext oauth2ClientContext) {
    this.oauth2ClientContext = oauth2ClientContext;
  }

  @Bean
  public Filter ssoFilter() {
    System.out.println("test");
    System.out.println(google().getClient().getClientId());
    System.out.println(google().getResource().getUserInfoUri());


    OAuth2ClientAuthenticationProcessingFilter oauth2Filter = new OAuth2ClientAuthenticationProcessingFilter("/login");
    OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(google().getClient(), oauth2ClientContext);
    oauth2Filter.setRestTemplate(oAuth2RestTemplate);
    oauth2Filter.setTokenServices(new UserInfoTokenServices(google().getResource().getUserInfoUri(), google().getClient().getClientId()));

    return oauth2Filter;
  }

  @Bean
  @ConfigurationProperties("google")
  public ClientResources google() {
    return new ClientResources();
  }

  @Bean
  public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }
}