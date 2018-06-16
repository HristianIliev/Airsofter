package hristian.iliev.airsofter.models.response;

public class ChartEntry {
  private String date;
  private int requests;

  public ChartEntry() {

  }

  public ChartEntry(String date, int requests) {
    this.setDate(date);
    this.setRequests(requests);
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getRequests() {
    return requests;
  }

  public void setRequests(int requests) {
    this.requests = requests;
  }

  @Override
  public String toString() {
    return "ChartEntry{" +
            "date='" + date + '\'' +
            ", requests=" + requests +
            '}';
  }
}
