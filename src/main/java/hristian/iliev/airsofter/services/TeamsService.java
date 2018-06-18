package hristian.iliev.airsofter.services;

import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.contracts.ITeamsService;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.Team;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.TeamWithMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamsService implements ITeamsService {
  private final IRepository<Team> teamsRepository;
  private final IRepository<ArenaCategory> arenaCategoriesRepository;
  private final IRepository<User> usersRepository;

  @Autowired
  public TeamsService(IRepository<Team> teamsRepository,
                      IRepository<ArenaCategory> arenaCategoriesRepository,
                      IRepository<User> usersRepository) {
    this.teamsRepository = teamsRepository;
    this.arenaCategoriesRepository = arenaCategoriesRepository;
    this.usersRepository = usersRepository;
  }

  @Override
  public Team createTeam(TeamWithMembers team, User owner) {
    Team toAdd = new Team();
    toAdd.setName(team.getName());
    toAdd.setDescription(team.getDescription());
    toAdd.setOwner(owner);
    toAdd.setTelephone(team.getTelephone());

    List<ArenaCategory> preferences = new ArrayList<>();
    for (int i = 0; i < team.getPreferences().size(); i++) {
      ArenaCategory toInsert = this.getArenaCategoryByName(team.getPreferences().get(i).getName().trim());
      preferences.add(toInsert);
    }

    toAdd.setPreferences(preferences);

    List<User> members = new ArrayList<>();
    for (int i = 0; i < team.getTeamMembers().size(); i++) {
      User user = this.getUserByEmail(team.getTeamMembers().get(i).getEmail());
      if (user == null) {
        return null;
      }

      members.add(user);
    }

    toAdd.setMembers(members);

    return this.teamsRepository.create(toAdd);
  }

  private User getUserByEmail(String email) {
    return this.usersRepository.getAll()
            .stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst()
            .orElse(null);
  }

  private ArenaCategory getArenaCategoryByName(String name) {
    return this.arenaCategoriesRepository.getAll().stream()
            .filter(ac -> ac.getName().equals(name))
            .findFirst()
            .orElse(null);
  }

  @Override
  public Team getTeamById(int id) {
    return null;
  }
}
