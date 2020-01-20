package com.subscribe.watch.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiApplication {

  private static final String PROPERTIES = "spring.config.location=classpath:/google.yml";

  public static void main(String[] args) {
    new SpringApplicationBuilder(ApiApplication.class)
            .properties(PROPERTIES)
            .run(args);
  }
}
