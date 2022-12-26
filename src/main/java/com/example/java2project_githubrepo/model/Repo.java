package com.example.java2project_githubrepo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Repo {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private int developerNum;
  private int most_active_developer;
  private int open_issues;
  private int close_issues;
  private double issue_solve_average;
  private long issue_extreme_value_diff;
  private double issue_variance;
  private int issue_solve_min_day;
  private int releases_num;
  private int commit_times;
  private int releases_top10_commits;
  private int releases_commits;

  private Date created_at;
  private Date updated_at;


  public double getIssue_variance() {
    return issue_variance;
  }

  public void setIssue_variance(double issue_variance) {
    this.issue_variance = issue_variance;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public Repo(String name, int developerNum, int most_active_developer, int open_issues,
      int close_issues, double issue_solve_average, int issue_solve_max_day,
      int issue_solve_min_day, int releases, int commit_times, int releases_top10_commits,
      int releases_commits) {
    this.name = name;
    this.developerNum = developerNum;
    this.most_active_developer = most_active_developer;
    this.open_issues = open_issues;
    this.close_issues = close_issues;
    this.issue_solve_average = issue_solve_average;
    this.issue_extreme_value_diff = issue_solve_max_day;
    this.issue_solve_min_day = issue_solve_min_day;
    this.releases_num = releases;
    this.commit_times = commit_times;
    this.releases_top10_commits = releases_top10_commits;
    this.releases_commits = releases_commits;
  }

  public Repo(Long id, String name, int developerNum, int most_active_developer, int open_issues,
      int close_issues, double issue_solve_average, int issue_solve_max_day,
      int issue_solve_min_day, int releases, int commit_times, int releases_top10_commits,
      int releases_commits) {
    this.id = id;
    this.name = name;
    this.developerNum = developerNum;
    this.most_active_developer = most_active_developer;
    this.open_issues = open_issues;
    this.close_issues = close_issues;
    this.issue_solve_average = issue_solve_average;
    this.issue_extreme_value_diff = issue_solve_max_day;
    this.issue_solve_min_day = issue_solve_min_day;
    this.releases_num = releases;
    this.commit_times = commit_times;
    this.releases_top10_commits = releases_top10_commits;
    this.releases_commits = releases_commits;
  }

  public Repo() {
    this.name = "name";
    this.developerNum = 0;
    this.most_active_developer = 0;
    this.open_issues = 0;
    this.close_issues = 0;
    this.issue_solve_average = 0;
    this.issue_extreme_value_diff = 0;
    this.issue_solve_min_day = 0;
    this.releases_num = 0;
    this.commit_times = 0;
    this.releases_top10_commits = 0;
    this.releases_commits = 0;
  }

  @Override
  public String toString() {
    return "Repo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", developerNum=" + developerNum +
        ", most_active_developer=" + most_active_developer +
        ", open_issues=" + open_issues +
        ", close_issues=" + close_issues +
        ", issue_solve_average=" + issue_solve_average +
        ", issue_solve_max_day=" + issue_extreme_value_diff +
        ", issue_solve_min_day=" + issue_solve_min_day +
        ", releases=" + releases_num +
        ", commit_times=" + commit_times +
        ", releases_top10_commits=" + releases_top10_commits +
        ", releases_commits=" + releases_commits +
        '}';
  }

  public String getName() {
    return name;
  }

  public int getDeveloperNum() {
    return developerNum;
  }

  public int getMost_active_developer() {
    return most_active_developer;
  }

  public int getOpen_issues() {
    return open_issues;
  }

  public int getClose_issues() {
    return close_issues;
  }

  public double getIssue_solve_average() {
    return issue_solve_average;
  }

  public long getIssue_extreme_value_diff() {
    return issue_extreme_value_diff;
  }

  public int getIssue_solve_min_day() {
    return issue_solve_min_day;
  }

  public int getReleases_num() {
    return releases_num;
  }

  public int getCommit_times() {
    return commit_times;
  }

  public int getReleases_top10_commits() {
    return releases_top10_commits;
  }

  public int getReleases_commits() {
    return releases_commits;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDeveloperNum(int developerNum) {
    this.developerNum = developerNum;
  }

  public void setMost_active_developer(int most_active_developer) {
    this.most_active_developer = most_active_developer;
  }

  public void setOpen_issues(int open_issues) {
    this.open_issues = open_issues;
  }

  public void setClose_issues(int close_issues) {
    this.close_issues = close_issues;
  }

  public void setIssue_solve_average(double issue_solve_average) {
    this.issue_solve_average = issue_solve_average;
  }

  public void setIssue_extreme_value_diff(long issue_extreme_value_diff) {
    this.issue_extreme_value_diff = issue_extreme_value_diff;
  }

  public void setIssue_solve_min_day(int issue_solve_min_day) {
    this.issue_solve_min_day = issue_solve_min_day;
  }

  public void setReleases_num(int releases_num) {
    this.releases_num = releases_num;
  }

  public void setCommit_times(int commit_times) {
    this.commit_times = commit_times;
  }

  public void setReleases_top10_commits(int releases_top10_commits) {
    this.releases_top10_commits = releases_top10_commits;
  }

  public void setReleases_commits(int releases_commits) {
    this.releases_commits = releases_commits;
  }
}
