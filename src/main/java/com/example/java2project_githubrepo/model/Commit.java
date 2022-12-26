package com.example.java2project_githubrepo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Commit {
  @Id
  @GeneratedValue
  private Long id;
  private String sha;
  private String repo;
  private String login;
  private Date commitdate;

  public void setId(Long id) {
    this.id = id;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }

  public void setRepo(String repo) {
    this.repo = repo;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setCommitdate(Date commitdate) {
    this.commitdate = commitdate;
  }

  public Long getId() {
    return id;
  }

  public String getSha() {
    return sha;
  }

  public String getRepo() {
    return repo;
  }

  public String getLogin() {
    return login;
  }

  public Date getCommitdate() {
    return commitdate;
  }
}
