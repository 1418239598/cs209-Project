package com.example.java2project_githubrepo.repository;

import com.example.java2project_githubrepo.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}

