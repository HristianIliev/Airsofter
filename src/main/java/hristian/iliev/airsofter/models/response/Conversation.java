package hristian.iliev.airsofter.models.response;

import hristian.iliev.airsofter.models.User;

public class Conversation {
  private User current;
  private User other;

  public Conversation(User current, User other){
    this.setCurrent(current);
    this.setOther(other);
  }

  public User getCurrent() {
    return current;
  }

  public void setCurrent(User current) {
    this.current = current;
  }

  public User getOther() {
    return other;
  }

  public void setOther(User other) {
    this.other = other;
  }
}
