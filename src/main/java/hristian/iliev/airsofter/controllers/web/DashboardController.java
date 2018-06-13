package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {
  private final IUsersService usersService;

  @Autowired
  public DashboardController(IUsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());
    if (user.isNeedsInstallation()) {
      if (user.isArenaOwner()) {
        return "redirect:/install-arena";
      } else if (!user.isArenaOwner()) {
        return "redirect:/install";
      }
    } else if(!user.isNeedsInstallation() && !user.isArenaOwner()){
      return "redirect:/app";
    }

    model.addAttribute("arenaName", user.getArena().getName());

    return "dashboard";
  }

  @GetMapping("/messages")
  public String messages(){
    return "messages";
  }

  @GetMapping("/preview")
  public String preview(){
    return "preview";
  }

  @GetMapping("/edit")
  public String edit(){
    return "edit";
  }

  @GetMapping("/profile")
  public String profile(){
    return "profile";
  }

  @GetMapping("/calendar")
  public String calendar(){
    return "calendar";
  }
}
