package hristian.iliev.airsofter.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstallationsController {
  @GetMapping("/install-arena")
  public String installArena(){
    return "install-arena";
  }

  @GetMapping("/install")
  public String install(){
    return "install";
  }
}
