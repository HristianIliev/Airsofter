package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IArenaCategoriesService;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ArenaOwnerController {
  private final IUsersService usersService;
  private final IArenaCategoriesService arenaCategoriesService;

  @Autowired
  public ArenaOwnerController(IUsersService usersService, IArenaCategoriesService arenaCategoriesService) {
    this.usersService = usersService;
    this.arenaCategoriesService = arenaCategoriesService;
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
    } else if (!user.isNeedsInstallation() && !user.isArenaOwner()) {
      return "redirect:/app";
    }

    model.addAttribute("arenaName", user.getArena().getName());

    return "dashboard";
  }

  @GetMapping("/preview")
  public String preview(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());

    boolean hasOtherPictures = false;
    for (int i = 0; i < user.getArenaPhotos().size(); i++) {
      if (!user.getArenaPhotos().get(i).isCover()) {
        hasOtherPictures = true;
      }
    }

    model.addAttribute("arenaName", user.getArena().getName());
    model.addAttribute("arenaDescription", user.getArena().getDescription());
    model.addAttribute("arenaTelephone", user.getArena().getTelephone());
    model.addAttribute("hasOtherPictures", hasOtherPictures);

    return "preview";
  }

  @GetMapping("/requests")
  public String requests(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());

    model.addAttribute("requests", user.getRequestsForTheArena());

    return "requests";
  }

  @GetMapping("/messages")
  public String messages() {
    return "messages";
  }

  @GetMapping("/edit")
  public String edit(Model model, Principal principal) {
    User user = this.usersService.getUserByEmail(principal.getName());
    List<ArenaCategory> arenaCategories = this.arenaCategoriesService.getAll();

    model.addAttribute("arenaCategories1", arenaCategories.subList(0, 9));
    model.addAttribute("arenaCategories2", arenaCategories.subList(9, 18));

    model.addAttribute("arenaName", user.getArena().getName());
    model.addAttribute("arena", user.getArena());

    return "edit";
  }

  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

  @GetMapping("/calendar")
  public String calendar() {
    return "calendar";
  }
}
