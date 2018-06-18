package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.ITeamsService;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.Team;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.TeamWithMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class TeamsController {
  private final ITeamsService teamsService;
  private final IUsersService usersService;

  @Autowired
  public TeamsController(ITeamsService teamsService,
                         IUsersService usersService) {
    this.teamsService = teamsService;
    this.usersService = usersService;
  }

  @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
  @ResponseBody
  public Team createTeam(@RequestBody TeamWithMembers team, Principal principal) {
    String userEmail = principal.getName();
    User current = this.usersService.getUserByEmail(userEmail);

    Team created = this.teamsService.createTeam(team, current);
    if(created.getId() != 0){
      this.usersService.installationCompleted(current);
    }

    return created;
  }

  @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Team getTeamById(@PathVariable String id) {
    return this.teamsService.getTeamById(Integer.parseInt(id));
  }
}
