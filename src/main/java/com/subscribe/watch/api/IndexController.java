package com.subscribe.watch.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
