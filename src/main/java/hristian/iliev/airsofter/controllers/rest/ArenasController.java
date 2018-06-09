package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IArenasService;
import hristian.iliev.airsofter.models.Arena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArenasController {
  private final IArenasService arenasService;

  @Autowired
  public ArenasController(IArenasService arenasService){
    this.arenasService = arenasService;
  }

  @RequestMapping(value = "/arena/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Arena getArena(@PathVariable String id){
    return this.arenasService.getArena(Integer.parseInt(id));
  }

  @RequestMapping(value = "/createArena", method = RequestMethod.POST)
  @ResponseBody
  public Arena createArena(@RequestBody Arena arena){
    System.out.println("reached");
    return this.arenasService.createArena(arena);
  }
}
