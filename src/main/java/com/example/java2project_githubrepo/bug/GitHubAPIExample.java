package com.example.java2project_githubrepo.bug;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GitHubAPIExample {

  public static void main(String[] args) {
    String[] repos = {"spring-projects/spring-security", "spring-projects/spring-kafka"};
    String[] types = {"contributors", "commits", "issues", "releases"};
    for (String repo : repos) {
      for (String type : types) {
        Bug(type, repo);
      }
    }
  }

  public static void Bug(String type, String repo) {
    StringBuffer total_content = new StringBuffer();
    File folder = new File(repo.split("/")[0]);
    folder.mkdirs();
    File folder2 = new File(repo.split("/")[0] + "/" + repo.split("/")[1]);
    folder2.mkdirs();
    File folder3 = new File(repo.split("/")[0] + "/" + repo.split("/")[1] + "/" + type);
    folder3.mkdirs();
    try {
      String state = "?state=all";
      String token = "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";
      String pages = "&per_page=100&page=";
      int page = 1;
      String urlString =
          "https://api.github.com/repos/" + repo + "/" + type + state
              + pages;

      while (true) {
        URL url = new URL(urlString + page);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/vnd.github+json");
        con.setRequestProperty("Authorization", "Token " + token);
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

//        con.setRequestProperty("token", token);
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
        con.disconnect();
        String result = content.toString();
        if (result.equals("[]")) {
          break;
        }
//        total_content.append(result);
        Path filePath = Paths.get(repo + "/" + type + "/" + page + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
          writer.write(result);
        }
        page++;
      }
    } catch (Exception e) {
      System.out.println("finished");
      e.printStackTrace();
    }

  }
}