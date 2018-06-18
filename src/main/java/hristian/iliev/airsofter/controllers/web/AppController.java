package hristian.iliev.airsofter.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class AppController {
  @GetMapping("/app")
  public String app() {
    return "app";
  }

  @GetMapping("/team/{id}")
  public String teamPage(@PathVariable String id) {
    return "team-page";
  }

  @GetMapping("/arena/{id}")
  public String arena(@PathVariable String id) {
    return "arena-page";
  }
}