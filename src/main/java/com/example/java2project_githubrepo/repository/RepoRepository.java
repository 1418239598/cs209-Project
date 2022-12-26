package com.example.java2project_githubrepo.repository;

import com.example.java2project_githubrepo.model.Repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repo, Long> {
  Repo findByName(String name);

  List<Repo> findByNameLike(String name);
}
