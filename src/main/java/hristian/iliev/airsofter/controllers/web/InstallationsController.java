package hristian.iliev.airsofter.controllers.web;

import hristian.iliev.airsofter.contracts.IArenaCategoriesService;
import hristian.iliev.airsofter.models.ArenaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InstallationsController {
  private final IArenaCategoriesService arenaCategoriesService;

  @Autowired
  public InstallationsController(IArenaCategoriesService arenaCategoriesService) {
    this.arenaCategoriesService = arenaCategoriesService;
  }

  @GetMapping("/install-arena")
  public String installArena(Model model) {
    List<ArenaCategory> arenaCategories = this.arenaCategoriesService.getAll();
    model.addAttribute("arenaCategories1", arenaCategories.subList(0, 9));
    model.addAttribute("arenaCategories2", arenaCategories.subList(9, 18));
    return "install-arena";
  }

  @GetMapping("/install")
  public String install() {
    return "install";
  }

  @GetMapping("/install-team")
  public String installTeam(Model model) {
    List<ArenaCategory> arenaCategories = this.arenaCategoriesService.getAll();
    model.addAttribute("arenaCategories1", arenaCategories.subList(0, 9));
    model.addAttribute("arenaCategories2", arenaCategories.subList(9, 18));
    return "install-team";
  }
}
