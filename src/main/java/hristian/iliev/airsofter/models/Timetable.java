package hristian.iliev.airsofter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristian.iliev.airsofter.contracts.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timetables")
public class Timetable implements IModel{
  private int id;
  private boolean mondayOff;
  private boolean tuesdayOff;
  private boolean wednesdayOff;
  private boolean thursdayOff;
  private boolean fridayOff;
  private boolean saturdayOff;
  private boolean sundayOff;
  private String mondayStart;
  private String mondayEnd;
  private String tuesdayStart;
  private String tuesdayEnd;
  private String wednesdayStart;
  private String wednesdayEnd;
  private String thursdayStart;
  private String thursdayEnd;
  private String fridayStart;
  private String fridayEnd;
  private String saturdayStart;
  private String saturdayEnd;
  private String sundayStart;
  private String sundayEnd;
  private Arena arena;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "arena_id")
  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "monday_off")
  public boolean isMondayOff() {
    return mondayOff;
  }

  public void setMondayOff(boolean mondayOff) {
    this.mondayOff = mondayOff;
  }

  @Column(name = "tuesday_off")
  public boolean isTuesdayOff() {
    return tuesdayOff;
  }

  public void setTuesdayOff(boolean tuesdayOff) {
    this.tuesdayOff = tuesdayOff;
  }

  @Column(name = "wednesday_off")
  public boolean isWednesdayOff() {
    return wednesdayOff;
  }

  public void setWednesdayOff(boolean wednesdayOff) {
    this.wednesdayOff = wednesdayOff;
  }

  @Column(name = "thursday_off")
  public boolean isThursdayOff() {
    return thursdayOff;
  }

  public void setThursdayOff(boolean thursdayOff) {
    this.thursdayOff = thursdayOff;
  }

  @Column(name = "friday_off")
  public boolean isFridayOff() {
    return fridayOff;
  }

  public void setFridayOff(boolean fridayOff) {
    this.fridayOff = fridayOff;
  }

  @Column(name = "saturday_off")
  public boolean isSaturdayOff() {
    return saturdayOff;
  }

  public void setSaturdayOff(boolean saturdayOff) {
    this.saturdayOff = saturdayOff;
  }

  @Column(name = "sunday_off")
  public boolean isSundayOff() {
    return sundayOff;
  }

  public void setSundayOff(boolean sundayOff) {
    this.sundayOff = sundayOff;
  }

  @Column(name = "monday_start")
  public String getMondayStart() {
    return mondayStart;
  }

  public void setMondayStart(String mondayStart) {
    this.mondayStart = mondayStart;
  }

  @Column(name = "monday_end")
  public String getMondayEnd() {
    return mondayEnd;
  }

  public void setMondayEnd(String mondayEnd) {
    this.mondayEnd = mondayEnd;
  }

  @Column(name = "tuesday_start")
  public String getTuesdayStart() {
    return tuesdayStart;
  }

  public void setTuesdayStart(String tuesdayStart) {
    this.tuesdayStart = tuesdayStart;
  }

  @Column(name = "tuesday_end")
  public String getTuesdayEnd() {
    return tuesdayEnd;
  }

  public void setTuesdayEnd(String tuesdayEnd) {
    this.tuesdayEnd = tuesdayEnd;
  }

  @Column(name = "wednesday_start")
  public String getWednesdayStart() {
    return wednesdayStart;
  }

  public void setWednesdayStart(String wednesdayStart) {
    this.wednesdayStart = wednesdayStart;
  }

  @Column(name = "wednesday_end")
  public String getWednesdayEnd() {
    return wednesdayEnd;
  }

  public void setWednesdayEnd(String wednesdayEnd) {
    this.wednesdayEnd = wednesdayEnd;
  }

  @Column(name = "thursday_start")
  public String getThursdayStart() {
    return thursdayStart;
  }

  public void setThursdayStart(String thursdayStart) {
    this.thursdayStart = thursdayStart;
  }

  @Column(name = "thursday_end")
  public String getThursdayEnd() {
    return thursdayEnd;
  }

  public void setThursdayEnd(String thursdayEnd) {
    this.thursdayEnd = thursdayEnd;
  }

  @Column(name = "friday_start")
  public String getFridayStart() {
    return fridayStart;
  }

  public void setFridayStart(String fridayStart) {
    this.fridayStart = fridayStart;
  }

  @Column(name = "friday_end")
  public String getFridayEnd() {
    return fridayEnd;
  }

  public void setFridayEnd(String fridayEnd) {
    this.fridayEnd = fridayEnd;
  }

  @Column(name = "saturday_start")
  public String getSaturdayStart() {
    return saturdayStart;
  }

  public void setSaturdayStart(String saturdayStart) {
    this.saturdayStart = saturdayStart;
  }

  @Column(name = "saturday_end")
  public String getSaturdayEnd() {
    return saturdayEnd;
  }

  public void setSaturdayEnd(String saturdayEnd) {
    this.saturdayEnd = saturdayEnd;
  }

  @Column(name = "sunday_start")
  public String getSundayStart() {
    return sundayStart;
  }

  public void setSundayStart(String sundayStart) {
    this.sundayStart = sundayStart;
  }

  @Column(name = "sunday_end")
  public String getSundayEnd() {
    return sundayEnd;
  }

  public void setSundayEnd(String sundayEnd) {
    this.sundayEnd = sundayEnd;
  }

  @OneToOne(fetch = FetchType.EAGER)
  @MapsId
  @JsonIgnore
  public Arena getArena() {
    return arena;
  }

  public void setArena(Arena arena) {
    this.arena = arena;
  }
}
