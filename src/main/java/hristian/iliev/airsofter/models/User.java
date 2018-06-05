package hristian.iliev.airsofter.models;

import hristian.iliev.airsofter.contracts.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements IModel {

  private int id;
  private String email;
  private String password;
  private String name;
  private String lastName;
  private boolean arenaOwner;
  private boolean needsInstallation;

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

  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "arena_owner")
  public boolean isArenaOwner() {
    return arenaOwner;
  }

  public void setArenaOwner(boolean arenaOwner) {
    this.arenaOwner = arenaOwner;
  }

  @Column(name = "needs_installation")
  public boolean isNeedsInstallation() {
    return needsInstallation;
  }

  public void setNeedsInstallation(boolean needsInstallation) {
    this.needsInstallation = needsInstallation;
  }
}
