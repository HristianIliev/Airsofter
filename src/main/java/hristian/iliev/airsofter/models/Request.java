package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import hristian.iliev.airsofter.contracts.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Request implements IModel {
  private int id;
  private User user;
  private User owner;
  private String daytime;
  private String information;
  private int numberOfParticipants;
  private String status;
  private String sendOn;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @ManyToOne
  @JsonIgnore
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne
  @JsonBackReference
  public User getOwner() {
    return owner;
  }

  public void setOwner(User arenaOwner) {
    this.owner = arenaOwner;
  }

  @Column(name = "daytime")
  public String getDaytime() {
    return daytime;
  }

  public void setDaytime(String daytime) {
    this.daytime = daytime;
  }

  @Column(name = "information")
  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  @Column(name = "number_of_participants")
  public int getNumberOfParticipants() {
    return numberOfParticipants;
  }

  public void setNumberOfParticipants(int numberOfParticipants) {
    this.numberOfParticipants = numberOfParticipants;
  }

  @Column(name = "status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Column(name = "send_on")
  public String getSendOn() {
    return sendOn;
  }

  public void setSendOn(String sendOn) {
    this.sendOn = sendOn;
  }
}
