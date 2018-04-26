package com.pgssoft.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

  @RequestMapping({"/useradd", "/activityadd", "/user", "/activity", "/attendance", "/activity/{\\w+}", "/user/{\\w+}"})
  public String index() {
    return "forward:/index.html";
  }
}
