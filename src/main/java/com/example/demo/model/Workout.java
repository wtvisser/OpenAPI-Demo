package com.example.demo.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Workout
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-08T12:59:20.852239Z[Etc/UTC]")
public class Workout {

  @JsonProperty("id")
  private String id;

  @JsonProperty("type")
  private String type;

  @JsonProperty("duration")
  private Integer duration;

  @JsonProperty("caloriesBurned")
  private Integer caloriesBurned;

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public Workout id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "123", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Workout type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  
  @Schema(name = "type", example = "Running", required = false)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Workout duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Duration in minutes
   * @return duration
  */
  
  @Schema(name = "duration", example = "30", description = "Duration in minutes", required = false)
  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Workout caloriesBurned(Integer caloriesBurned) {
    this.caloriesBurned = caloriesBurned;
    return this;
  }

  /**
   * Get caloriesBurned
   * @return caloriesBurned
  */
  
  @Schema(name = "caloriesBurned", example = "300", required = false)
  public Integer getCaloriesBurned() {
    return caloriesBurned;
  }

  public void setCaloriesBurned(Integer caloriesBurned) {
    this.caloriesBurned = caloriesBurned;
  }

  public Workout date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", example = "Sun Oct 01 00:00:00 UTC 2023", required = false)
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workout workout = (Workout) o;
    return Objects.equals(this.id, workout.id) &&
        Objects.equals(this.type, workout.type) &&
        Objects.equals(this.duration, workout.duration) &&
        Objects.equals(this.caloriesBurned, workout.caloriesBurned) &&
        Objects.equals(this.date, workout.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, duration, caloriesBurned, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Workout {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    caloriesBurned: ").append(toIndentedString(caloriesBurned)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

