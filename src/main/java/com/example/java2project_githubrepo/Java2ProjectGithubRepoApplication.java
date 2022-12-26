package com.example.java2project_githubrepo;

import com.example.java2project_githubrepo.service.RepoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Java2ProjectGithubRepoApplication {

  public static void main(String[] args) {
    SpringApplication.run(Java2ProjectGithubRepoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(RepoService service){
    return args -> {
//      service.addRepos();
//      service.ReadLocalData("spring-projects/spring-boot");
//      service.ReadLocalData("spring-projects/spring-kafka");
//      service.ReadLocalData("spring-projects/spring-security");
//      service.computeCommitBetweenReleases("spring-boot");
//      service.computeCommitBetweenReleases("spring-kafka");
//      service.computeCommitBetweenReleases("spring-security");

    };
  }
}
