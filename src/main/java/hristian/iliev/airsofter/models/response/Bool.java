package hristian.iliev.airsofter.models.response;

public class Bool {
  private boolean ok;

  public Bool(boolean ok){
    this.setOk(ok);
  }

  public boolean isOk() {
    return ok;
  }

  public void setOk(boolean ok) {
    this.ok = ok;
  }
}
