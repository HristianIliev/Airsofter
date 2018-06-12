package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristian.iliev.airsofter.contracts.IModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "arenas")
public class Arena implements IModel {
  private int id;
  private String name;
  private String description;
  private double latitude;
  private double longitude;
  private List<ArenaCategory> arenaCategories;
  private String telephone;
  private Timetable timetable;
  private User owner;

  @GenericGenerator(name = "generator_arena", strategy = "foreign",
          parameters = @Parameter(name = "property", value = "owner"))
  @Id
  @GeneratedValue(generator = "generator_arena")
  @Column(name = "owner_id")
  @Override
  public int getId() {
    return id;
  }

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

  @Column(name = "latitude")
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  @Column(name = "longitude")
  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "arena_categories_arenas", joinColumns = {
          @JoinColumn(name = "id_arena")}, inverseJoinColumns = {@JoinColumn(name = "id_arena_category")})
  public List<ArenaCategory> getArenaCategories() {
    return arenaCategories;
  }

  public void setArenaCategories(List<ArenaCategory> arenaCategories) {
    this.arenaCategories = arenaCategories;
  }

  @Column(name = "telephone")
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "arena")
  public Timetable getTimetable() {
    return timetable;
  }

  public void setTimetable(Timetable timetable) {
    this.timetable = timetable;
  }

  @OneToOne()
  @PrimaryKeyJoinColumn
  @JsonIgnore
  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
