package com.example.java2project_githubrepo.repository;

import com.example.java2project_githubrepo.model.Contributor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
  List<Contributor> findTop5ByRepoOrderByContributionsDesc(String repo);
}
