package com.example.java2project_githubrepo.controller;

import com.example.java2project_githubrepo.model.Commit;
import com.example.java2project_githubrepo.service.RepoService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commits")
public class CommitRestController {

  private final RepoService repoService;

  public CommitRestController(RepoService repoService) {
    this.repoService = repoService;
  }

  @GetMapping
  public List<Commit> getCommitByLogin(@RequestParam(value = "login") Optional<String> login) {
    if (login.isPresent()) {
      return repoService.findByLoginLike(login.get());
    }
    return repoService.getCommits();
  }

}
