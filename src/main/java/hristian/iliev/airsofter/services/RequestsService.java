package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.contracts.IRequestsService;
import hristian.iliev.airsofter.models.Request;
import hristian.iliev.airsofter.models.response.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestsService implements IRequestsService {
  private final IRepository<Request> requestsRepository;

  @Autowired
  public RequestsService(IRepository<Request> requestsRepository) {
    this.requestsRepository = requestsRepository;
  }

  @Override
  public Bool rejectRequest(int id) {
    Request request = this.requestsRepository.getById(id);
    request.setStatus("REJECTED");
    this.requestsRepository.update(request);

    return new Bool(true);
  }

  @Override
  public Bool acceptRequest(int id) {
    Request request = this.requestsRepository.getById(id);
    request.setStatus("ACCEPTED");
    this.requestsRepository.update(request);

    return new Bool(true);
  }
}
