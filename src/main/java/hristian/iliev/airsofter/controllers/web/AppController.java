package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController {
  private final IUsersService usersService;
  private final IArenasService arenasService;

  @Autowired
  public AppController(IUsersService usersService,
                       IArenasService arenasService) {
    this.usersService = usersService;
    this.arenasService = arenasService;
  }

  @GetMapping("/app")
  public String app(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());

    boolean hasTeams = false;
    if (user.getCreatedTeams().size() != 0) {
      hasTeams = true;
    }

    List<Arena> list = this.arenasService.list().stream()
            .sorted((o1, o2) -> o2.getRating().compareTo(o1.getRating()))
            .collect(Collectors.toList())
            .subList(0, 3);

    model.addAttribute("requests", user.getMadeRequests());
    model.addAttribute("arenas", list);
    model.addAttribute("hasTeams", hasTeams);
    model.addAttribute("user", user);

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