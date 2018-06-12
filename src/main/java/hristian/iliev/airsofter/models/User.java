package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hristian.iliev.airsofter.contracts.IModel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

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
  private Arena arena;
  private List<Request> madeRequests;
  private List<Request> requestsForTheArena;
  private Integer lastConversationWith;

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

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
  public Arena getArena() {
    return arena;
  }

  public void setArena(Arena arena) {
    this.arena = arena;
  }

  @OneToMany(mappedBy = "user")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<Request> getMadeRequests() {
    return madeRequests;
  }

  public void setMadeRequests(List<Request> madeRequests) {
    this.madeRequests = madeRequests;
  }

  @OneToMany(mappedBy = "owner")
  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<Request> getRequestsForTheArena() {
    return requestsForTheArena;
  }

  public void setRequestsForTheArena(List<Request> requestsForTheArena) {
    this.requestsForTheArena = requestsForTheArena;
  }

  @Column(name = "last_conversation_with")
  public Integer getLastConversationWith() {
    return lastConversationWith;
  }

  public void setLastConversationWith(Integer lastConversationWith) {
    this.lastConversationWith = lastConversationWith;
  }
}
