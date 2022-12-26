package com.example.java2project_githubrepo.repository;

import com.example.java2project_githubrepo.model.Commit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Long> {
  List<Commit> findAllByRepoOrderByCommitdateAsc(String repo);

  List<Commit> findByLoginLike(String s);
}

