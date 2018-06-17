package hristian.iliev.airsofter.controllers.rest;

import hristian.iliev.airsofter.contracts.IRequestsService;
import hristian.iliev.airsofter.models.response.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RequestsController {
  private final IRequestsService requestsService;

  @Autowired
  public RequestsController(IRequestsService requestsService){
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

  @RequestMapping(value = "/markAsDone/{id}", method = RequestMethod.GET)
  public Bool markAsDone(@PathVariable String id){
    return this.requestsService.markAsDone(Integer.parseInt(id));
  }
}
