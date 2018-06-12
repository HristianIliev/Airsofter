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
  private String day;

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

  @Column(name = "day")
  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }
}
