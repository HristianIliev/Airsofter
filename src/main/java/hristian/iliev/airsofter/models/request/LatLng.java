package hristian.iliev.airsofter.models.request;

public class LatLng {
  private double latitude;
  private double longitude;

  public LatLng() {

  }

  public LatLng(double latitude, double longitude) {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
