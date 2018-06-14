package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PreviewController {
  private final IUsersService usersService;

  @Autowired
  public PreviewController(IUsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping("/preview")
  public String preview(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());

    model.addAttribute("arenaName", user.getArena().getName());
    model.addAttribute("arenaDescription", user.getArena().getDescription());
    model.addAttribute("arenaTelephone", user.getArena().getTelephone());

    return "preview";
  }
}
