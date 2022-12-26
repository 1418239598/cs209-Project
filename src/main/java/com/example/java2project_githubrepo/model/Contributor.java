package com.example.java2project_githubrepo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Contributor {

  /*
  login：贡献者的 GitHub 用户名。
  id：贡献者的 GitHub 用户 ID。
  node_id：贡献者的唯一标识符。
  avatar_url：贡献者的头像图片的 URL。
  gravatar_id：贡献者的 Gravatar ID。
  url：贡献者的 GitHub 个人主页的 URL。
  html_url：贡献者的 GitHub 个人主页的 HTML URL。
  followers_url：贡献者的粉丝列表的 URL。
  following_url：贡献者关注的用户列表的 URL。
  gists_url：贡献者的 Gists 列表的 URL。
  starred_url：贡献者标记的项目列表的 URL。
  subscriptions_url：贡献者订阅的项目列表的 URL。
  organizations_url：贡献者加入的组织的 URL。
  repos_url：贡献者的仓库列表的 URL。
  events_url：贡献者发生的事件的 URL。
  received_events_url：贡献者收到的事件的 URL。
  type：贡献者的类型（通常是 "User"）。
  site_admin：一个布尔值，指示贡献者是否是项目的管理员。
   */
  @Id
  @Column(name = "login", nullable = false)
  private String login;
  private String repo;
  private int contributions;

  public String getLogin() {
    return login;
  }

  public String getRepo() {
    return repo;
  }

  public int getContributions() {
    return contributions;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setRepo(String repo) {
    this.repo = repo;
  }

  public void setContributions(int contributions) {
    this.contributions = contributions;
  }
}
