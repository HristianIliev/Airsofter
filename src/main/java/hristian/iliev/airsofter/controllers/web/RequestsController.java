package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class RequestsController {
  private final IUsersService usersService;

  @Autowired
  public RequestsController(IUsersService usersService){
    this.usersService = usersService;
  }

  @GetMapping("/requests")
  public String requests(Model model, Principal principal){
    User user = this.usersService.getUserByEmail(principal.getName());

    model.addAttribute("requests", user.getRequestsForTheArena());

    return "requests";
  }
}
