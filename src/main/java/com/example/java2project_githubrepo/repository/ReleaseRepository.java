package com.example.java2project_githubrepo.repository;

import com.example.java2project_githubrepo.model.Release;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReleaseRepository extends JpaRepository<Release, Long> {
  List<Release> findAllByRepoOrderByUpdatedAsc(String repo);

  List<Release> findTop15ByRepoOrderByCommitsDesc(String repo);

  @Query("SELECT r FROM Release r WHERE r.repo = :repo AND r.name!='' ORDER BY r.commits DESC LIMIT 15")
  List<Release> findTop15ByRepoAndNameIsNotNull(@Param("repo") String repo);

//  List<Release> findByReleaseMoreThan(Integer integer);

  List<Release> findByCommitsGreaterThan(Integer integer);
}

