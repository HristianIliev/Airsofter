package hristian.iliev.airsofter.models.request;

public class TeamMember {
  private String email;
  private String level;

  public TeamMember() {

  }

  public TeamMember(String email, String level) {
    this.setEmail(email);
    this.setLevel(level);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return "TeamMember{" +
            "email='" + email + '\'' +
            ", level='" + level + '\'' +
            '}';
  }
}
