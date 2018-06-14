package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hristian.iliev.airsofter.contracts.IModel;
import hristian.iliev.airsofter.models.Arena;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "arena_photos")
public class ArenaPhoto implements IModel {
  private int id;
  private User owner;
  private byte[] picture;
  private boolean cover;

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

  @Lob
  @Column(name = "picture")
  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  @Column(name = "cover")
  public boolean isCover() {
    return cover;
  }

  public void setCover(boolean cover) {
    this.cover = cover;
  }

  @ManyToOne
  @JsonBackReference
  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
