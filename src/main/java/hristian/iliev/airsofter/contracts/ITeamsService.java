package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Team;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.TeamWithMembers;

public interface ITeamsService {
  Team createTeam(TeamWithMembers team, User owner);

  Team getTeamById(int id);
}
