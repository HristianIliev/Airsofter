package hristian.iliev.airsofter.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
  @GetMapping("/register")
  public String register(){
    return "register";
  }
}
