package com.example.java2project_githubrepo.controller;

import com.example.java2project_githubrepo.model.Commit;
import com.example.java2project_githubrepo.model.Repo;
import com.example.java2project_githubrepo.service.RepoService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repos")
public class RepoRestController {
  private final RepoService repoService;

  public RepoRestController(RepoService repoService) {
    this.repoService = repoService;
  }

  @GetMapping
  public List<Repo> getReposByName(@RequestParam(value = "name") Optional<String> name) {
    if (name.isPresent()) {
      return repoService.findByNameLike(name.get());
    }
    return repoService.getRepos();
  }



}
