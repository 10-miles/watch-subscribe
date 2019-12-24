package com.subscribe.watch.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoogleController {

  @RequestMapping("/GoogleLogin")
  public String join(){
    System.out.println("Google Login redirect Success! ");
//    return ""
  }
}
