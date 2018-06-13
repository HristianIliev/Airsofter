package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.response.Bool;

public interface IRequestsService {
  Bool rejectRequest(int id);

  Bool acceptRequest(int id);
}
