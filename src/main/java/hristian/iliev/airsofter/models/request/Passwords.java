package hristian.iliev.airsofter.models.request;

public class Passwords {
  private String oldPassword;
  private String newPassword;

  public Passwords() {

  }

  public Passwords(String oldPassword, String newPassword) {
    this.setOldPassword(oldPassword);
    this.setNewPassword(newPassword);
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
