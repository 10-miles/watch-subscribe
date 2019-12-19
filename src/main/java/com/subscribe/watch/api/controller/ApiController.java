package com.subscribe.watch.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @GetMapping("/")
  public String test() {
    return "hello";
  }
}
