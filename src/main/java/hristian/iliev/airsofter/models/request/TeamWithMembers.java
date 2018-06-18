package hristian.iliev.airsofter.models.request;

import hristian.iliev.airsofter.models.ArenaCategory;

import java.util.List;

public class TeamWithMembers {
  private String name;
  private String description;
  private String telephone;
  private List<ArenaCategory> preferences;
  private List<TeamMember> teamMembers;

  public TeamWithMembers() {

  }

  public TeamWithMembers(String name,
                         String description,
                         String telephone,
                         List<ArenaCategory> preferences,
                         List<TeamMember> teamMembers) {
    this.setName(name);
    this.setDescription(description);
    this.setTelephone(telephone);
    this.setPreferences(preferences);
    this.setTeamMembers(teamMembers);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public List<ArenaCategory> getPreferences() {
    return preferences;
  }

  public void setPreferences(List<ArenaCategory> preferences) {
    this.preferences = preferences;
  }

  public List<TeamMember> getTeamMembers() {
    return teamMembers;
  }

  public void setTeamMembers(List<TeamMember> teamMembers) {
    this.teamMembers = teamMembers;
  }

  @Override
  public String toString() {
    return "TeamWithMembers{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", telephone='" + telephone + '\'' +
            ", preferences=" + preferences +
            ", teamMembers=" + teamMembers +
            '}';
  }
}
