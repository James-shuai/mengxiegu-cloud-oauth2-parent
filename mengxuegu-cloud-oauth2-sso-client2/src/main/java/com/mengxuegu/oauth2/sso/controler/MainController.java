package com.mengxuegu.oauth2.sso.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ys
 * @date 2020/4/23 14:10
 */
@Controller
public class MainController {

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/member")
  public String member(){
    return "member";
  }


}
