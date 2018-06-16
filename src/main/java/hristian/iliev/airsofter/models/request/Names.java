package hristian.iliev.airsofter.models.request;

public class Names {
  private String name;
  private String lastName;

  public Names() {

  }

  public Names(String name, String lastName) {
    this.setName(name);
    this.setLastName(lastName);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
