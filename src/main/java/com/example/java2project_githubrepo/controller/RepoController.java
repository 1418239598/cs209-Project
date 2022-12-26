package com.example.java2project_githubrepo.controller;

import com.example.java2project_githubrepo.service.RepoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RepoController {
  private final RepoService repoService;

  static String[] repos={"spring-projects/spring-boot","spring-projects/spring-kafka","spring-projects/spring-security"};

  public RepoController(RepoService repoService) {
    this.repoService = repoService;
  }

  @RequestMapping("/repo1")
  public String getRepos1(Model model){
//    model.addAttribute("repos",repoService.getRepos());
    model.addAttribute("repo",repoService.getRepo(repos[0].split("/")[1]));
    model.addAttribute("repo_url",repoService.getRepo(repos[0].split("/")[1]).getName());
    model.addAttribute("contributors",repoService.getContributors(repos[0].split("/")[1]));
    model.addAttribute("data",repoService.getTopCommitReleases(repos[0].split("/")[1]));
    model.addAttribute("releasename",repoService.getTopCommitReleasesName(repos[0].split("/")[1]));
    model.addAttribute("commitpermonth",repoService.getCommitTimes_Month(repos[0]));
    model.addAttribute("commitperhour",repoService.getCommitTimes_Hour(repos[0]));
    return "index4";
  }

  @RequestMapping("/repo2")
  public String getRepos2(Model model){
//    model.addAttribute("repos",repoService.getRepos());
    model.addAttribute("repo",repoService.getRepo(repos[1].split("/")[1]));
    model.addAttribute("repo_url",repoService.getRepo(repos[1].split("/")[1]).getName());
    model.addAttribute("contributors",repoService.getContributors(repos[1].split("/")[1]));
    model.addAttribute("data",repoService.getTopCommitReleases(repos[1].split("/")[1]));
    model.addAttribute("releasename",repoService.getTopCommitReleasesName(repos[1].split("/")[1]));
    model.addAttribute("commitpermonth",repoService.getCommitTimes_Month(repos[1]));
    model.addAttribute("commitperhour",repoService.getCommitTimes_Hour(repos[1]));
    return "index4";
  }

  @RequestMapping("/repo3")
  public String getRepos3(Model model){
//    model.addAttribute("repos",repoService.getRepos());
    model.addAttribute("repo",repoService.getRepo(repos[2].split("/")[1]));
    model.addAttribute("repo_url",repoService.getRepo(repos[2].split("/")[1]).getName());
    model.addAttribute("contributors",repoService.getContributors(repos[2].split("/")[1]));
    model.addAttribute("data",repoService.getTopCommitReleases(repos[2].split("/")[1]));
    model.addAttribute("releasename",repoService.getTopCommitReleasesName(repos[2].split("/")[1]));
    model.addAttribute("commitpermonth",repoService.getCommitTimes_Month(repos[2]));
    model.addAttribute("commitperhour",repoService.getCommitTimes_Hour(repos[2]));
    return "index4";
  }
}
