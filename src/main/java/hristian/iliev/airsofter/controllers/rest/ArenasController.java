package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ArenasController {
  private final IArenasService arenasService;
  private final IUsersService usersService;

  @Autowired
  public ArenasController(IArenasService arenasService,
                          IUsersService usersService) {
    this.arenasService = arenasService;
    this.usersService = usersService;
  }

  @RequestMapping(value = "/arena/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Arena getArena(@PathVariable String id) {
    return this.arenasService.getArena(Integer.parseInt(id));
  }

  @RequestMapping(value = "/arena", method = RequestMethod.GET)
  @ResponseBody
  public Arena arena(Principal principal) {
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);

    return user.getArena();
  }

  @RequestMapping(value = "/createArena", method = RequestMethod.POST)
  @ResponseBody
  public Arena createArena(@RequestBody Arena arena, Principal principal) {
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);
    Arena created = this.arenasService.createArena(arena, user);
    if (created.getId() != 0) {
      this.usersService.installationCompleted(user);
    }

    return created;
  }
}
