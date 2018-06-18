package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import hristian.iliev.airsofter.contracts.IModel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team implements IModel {
  private int id;
  private String name;
  private String description;
  private String telephone;
  private User owner;
  private List<ArenaCategory> preferences;
  private List<User> members;

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

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "telephone")
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @ManyToOne
  @JsonIgnore
  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "arena_categories_teams", joinColumns = {
          @JoinColumn(name = "id_team")}, inverseJoinColumns = {@JoinColumn(name = "id_arena_category")})
  public List<ArenaCategory> getPreferences() {
    return preferences;
  }

  public void setPreferences(List<ArenaCategory> preferences) {
    this.preferences = preferences;
  }

  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "teams_users", joinColumns = {
          @JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
  @JsonIgnore
  public List<User> getMembers() {
    return members;
  }

  public void setMembers(List<User> members) {
    this.members = members;
  }
}
