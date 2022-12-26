package com.example.java2project_githubrepo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Issue {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(columnDefinition = "VARCHAR(1000)")
  private String title;

  Date created_at;

  Date updated_at;

  Boolean state;

  Long solve_time;

  String repo_name;

  public void setRepo_name(String repo_name) {
    this.repo_name = repo_name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public void setState(Boolean state) {
    this.state = state;
  }

  public void setSolve_time(Long solve_time) {
    this.solve_time = solve_time;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public Boolean getState() {
    return state;
  }

  public Long getSolve_time() {
    return solve_time;
  }

  public String getRepo_name() {
    return repo_name;
  }
}
