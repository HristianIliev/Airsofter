package hristian.iliev.airsofter.models.request;

import hristian.iliev.airsofter.models.ArenaCategory;

import java.util.List;

public class ArenaMainSettings {
  private String name;
  private String description;
  private String telephone;
  private List<ArenaCategory> arenaCategories;

  public ArenaMainSettings() {

  }

  public ArenaMainSettings(String name, String description, String telephone, List<ArenaCategory> arenaCategories) {
    this.setName(name);
    this.setDescription(description);
    this.setTelephone(telephone);
    this.setArenaCategories(arenaCategories);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public List<ArenaCategory> getArenaCategories() {
    return arenaCategories;
  }

  public void setArenaCategories(List<ArenaCategory> arenaCategories) {
    this.arenaCategories = arenaCategories;
  }
}
