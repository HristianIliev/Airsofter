package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.contracts.IUsersService;
import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.ArenaMainSettings;
import hristian.iliev.airsofter.models.request.LatLng;
import hristian.iliev.airsofter.models.response.ChartData;
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
import java.text.ParseException;
import java.util.List;

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

  @RequestMapping(value = "/arenas", method = RequestMethod.GET)
  @ResponseBody
  public List<Arena> arenas() {
    return this.arenasService.getAll();
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

  @RequestMapping(value = "/changeArenaMainSettings", method = RequestMethod.POST)
  @ResponseBody
  public Arena changeArenaMainSettings(@RequestBody ArenaMainSettings arenaMainSettings, Principal principal) {
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);

    return this.arenasService.changeArenaMainSettings(user, arenaMainSettings);
  }

  @RequestMapping(value = "/changeLatLng", method = RequestMethod.POST)
  @ResponseBody
  public Arena changeLatLng(@RequestBody LatLng latLng, Principal principal) {
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);

    return this.arenasService.changeLatLng(user, latLng);
  }

  @RequestMapping(value = "/changeTimetable", method = RequestMethod.POST)
  @ResponseBody
  public Arena changeTimetable(@RequestBody Timetable timetable, Principal principal){
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);

    return this.arenasService.changeTimetable(user, timetable);
  }

  @RequestMapping(value = "/getChartData", method = RequestMethod.GET)
  @ResponseBody
  public ChartData getChartData(Principal principal) throws ParseException {
    String userEmail = principal.getName();
    User user = this.usersService.getUserByEmail(userEmail);

    System.out.println(this.arenasService.getChartData(user));
    return this.arenasService.getChartData(user);
  }
}
