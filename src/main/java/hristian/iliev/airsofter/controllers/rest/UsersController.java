package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UsersController {
  private final IUsersService usersService;

  @Autowired
  public UsersController(IUsersService usersService) {
    this.usersService = usersService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public User register(@RequestBody User user) {
    return this.usersService.register(user);
  }

  @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
  @ResponseBody
  public User getCurrentUser(Principal principal){
    String userEmail = principal.getName();
    return this.usersService.getUserByEmail(userEmail);
  }
}
