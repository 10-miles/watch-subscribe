package com.subscribe.watch.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "hello 'Watch Your Subscribe'";
  }

  @PostMapping("/")
  public String test() {
    return "post test";
  }
}
