package hristian.iliev.airsofter.models.helper;

import hristian.iliev.airsofter.models.Request;

import java.util.List;

public class RequestsInformation {
  private Request nextRequest;
  private int clientsForToday;
  private String timeUntilNextClient;
  private int pendingRequests;
  private int doneClients;
  private List<Request> requestsForTheDay;
  private List<Request> requestsToAccept;

  public RequestsInformation() {

  }

  public RequestsInformation(Request nextRequest,
                             int clientsForToday,
                             String timeUntilNextClient,
                             int pendingRequests,
                             int doneClients,
                             List<Request> requestsForTheDay,
                             List<Request> requestsToAccept) {
    this.setNextRequest(nextRequest);
    this.setClientsForToday(clientsForToday);
    this.setTimeUntilNextClient(timeUntilNextClient);
    this.setPendingRequests(pendingRequests);
    this.setDoneClients(doneClients);
    this.setRequestsForTheDay(requestsForTheDay);
    this.setRequestsToAccept(requestsToAccept);
  }

  public Request getNextRequest() {
    return nextRequest;
  }

  public void setNextRequest(Request nextRequest) {
    this.nextRequest = nextRequest;
  }

  public int getClientsForToday() {
    return clientsForToday;
  }

  public void setClientsForToday(int clientsForToday) {
    this.clientsForToday = clientsForToday;
  }

  public String getTimeUntilNextClient() {
    return timeUntilNextClient;
  }

  public void setTimeUntilNextClient(String timeUntilNextClient) {
    this.timeUntilNextClient = timeUntilNextClient;
  }

  public int getPendingRequests() {
    return pendingRequests;
  }

  public void setPendingRequests(int pendingRequests) {
    this.pendingRequests = pendingRequests;
  }

  public int getDoneClients() {
    return doneClients;
  }

  public void setDoneClients(int doneClients) {
    this.doneClients = doneClients;
  }

  public List<Request> getRequestsForTheDay() {
    return requestsForTheDay;
  }

  public void setRequestsForTheDay(List<Request> requestsForTheDay) {
    this.requestsForTheDay = requestsForTheDay;
  }

  @Override
  public String toString() {
    return "RequestsInformation{" +
            "nextRequest=" + nextRequest +
            ", clientsForToday=" + clientsForToday +
            ", timeUntilNextClient='" + timeUntilNextClient + '\'' +
            ", pendingRequests=" + pendingRequests +
            ", doneClients=" + doneClients +
            ", requestsForTheDay=" + requestsForTheDay +
            '}';
  }

  public List<Request> getRequestsToAccept() {
    return requestsToAccept;
  }

  public void setRequestsToAccept(List<Request> requestsToAccept) {
    this.requestsToAccept = requestsToAccept;
  }
}
