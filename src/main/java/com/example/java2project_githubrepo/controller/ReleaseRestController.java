package com.example.java2project_githubrepo.controller;

import com.example.java2project_githubrepo.model.Commit;
import com.example.java2project_githubrepo.model.Release;
import com.example.java2project_githubrepo.service.RepoService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/releases")
public class ReleaseRestController {

  private final RepoService repoService;

  public ReleaseRestController(RepoService repoService) {
    this.repoService = repoService;
  }

  @GetMapping("/morethan")
  public List<Release> getReleaseMoreThan(@RequestParam(value = "num") Optional<Integer> num) {
    if (num.isPresent()) {
      return repoService.findByCommitsMoreThan(num.get());
    }
    return repoService.getReleases();
  }

}
