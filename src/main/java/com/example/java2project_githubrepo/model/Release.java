package com.example.java2project_githubrepo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Release {
  @Id
  @GeneratedValue
  private Long id;
  private String repo;
  private Date created_at;
  private Date updated;
  private String name;

  private int commits;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCommits(int commits) {
    this.commits = commits;
  }

  public int getCommits() {
    return commits;
  }

  public Long getId() {
    return id;
  }

  public String getRepo() {
    return repo;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setRepo(String repo) {
    this.repo = repo;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
