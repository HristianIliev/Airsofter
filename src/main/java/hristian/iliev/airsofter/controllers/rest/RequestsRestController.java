package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IRequestsService;
import hristian.iliev.airsofter.controllers.web.RequestsController;
import hristian.iliev.airsofter.models.response.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RequestsRestController {
  private final IRequestsService requestsService;

  @Autowired
  public RequestsRestController(IRequestsService requestsService){
    this.requestsService = requestsService;
  }

  @RequestMapping(value = "/reject/{id}", method = RequestMethod.GET)
  public Bool rejectRequest(@PathVariable String id){
    return this.requestsService.rejectRequest(Integer.parseInt(id));
  }

  @RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
  public Bool acceptRequest(@PathVariable String id){
    return this.requestsService.acceptRequest(Integer.parseInt(id));
  }
}
