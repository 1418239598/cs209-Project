package com.example.java2project_githubrepo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.java2project_githubrepo.model.Commit;
import com.example.java2project_githubrepo.model.Contributor;
import com.example.java2project_githubrepo.model.Issue;
import com.example.java2project_githubrepo.model.Release;
import com.example.java2project_githubrepo.model.Repo;
import com.example.java2project_githubrepo.repository.CommitRepository;
import com.example.java2project_githubrepo.repository.ContributorRepository;
import com.example.java2project_githubrepo.repository.IssueRepository;
import com.example.java2project_githubrepo.repository.ReleaseRepository;
import com.example.java2project_githubrepo.repository.RepoRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoService {

  private final RepoRepository repoRepository;
  private final ContributorRepository contributorRepository;
  private final IssueRepository issueRepository;
  private final ReleaseRepository releaseRepository;
  private final CommitRepository commitRepository;

  @Autowired
  public RepoService(RepoRepository repoRepository, ContributorRepository contributorRepository,
      IssueRepository issueRepository, ReleaseRepository releaseRepository,
      CommitRepository commitRepository) {
    this.repoRepository = repoRepository;
    this.contributorRepository = contributorRepository;
    this.issueRepository = issueRepository;
    this.releaseRepository = releaseRepository;
    this.commitRepository = commitRepository;
  }

  public List<Repo> getRepos() {
    return repoRepository.findAll();
  }

  public List<Repo> findByNameLike(String name) {
    return repoRepository.findByNameLike(name);
  }

  public Repo getRepo(String name) {
    return repoRepository.findByName(name);
  }

  public List<Contributor> getContributors(String repo) {
    return contributorRepository.findTop5ByRepoOrderByContributionsDesc(repo);
  }

  public List<Integer> getTopCommitReleases(String repo) {
    List<Integer> topCommits = releaseRepository.findTop15ByRepoOrderByCommitsDesc(repo).stream()
        .map(Release::getCommits).toList();
    System.out.println(topCommits.toString());
    return topCommits;
  }

  public List<String> getTopCommitReleasesName(String repo) {
    List<String> topCommits = releaseRepository.findTop15ByRepoAndNameIsNotNull(repo).stream()
        .map(Release::getName).toList();
//    List<String> topCommits=releaseRepository.findTop15ByRepoOrderByCommitsDesc(repo).stream().map(release -> {
//      if (release.getName().equals("") || release.getName().isEmpty()) {
//        return "noname";
//      }
//      return release.getName();
//    }).toList();
    System.out.println(topCommits.toString());
    return topCommits;
  }

  public List<Integer> getCommitTimes_Month(String repo) {
    try {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(new FileInputStream(repo + "/ctm.txt"), StandardCharsets.UTF_8));
      String inputLine = null;
      List<Integer> commits = new ArrayList<>();
      while ((inputLine = in.readLine()) != null) {
        int a = Integer.parseInt(inputLine);
        commits.add(a);
      }
      return commits;
    } catch (Exception e) {
      e.printStackTrace();
    }
    int[] commits = new int[12];
    List<Date> commitTimes = commitRepository.findAllByRepoOrderByCommitdateAsc(repo.split("/")[1]).stream()
        .map(Commit::getCommitdate).toList();
    for (Date commitTime : commitTimes) {
      int a = commitTime.getMonth();
      commits[a]++;
    }
    System.out.println(commits.toString());
    Path filePath = Paths.get(repo + "/ctm.txt");
    try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
        StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
      for(int i=0;i<12;i++){
        writer.write(commits[i] + "\r\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return List.of(commits[0], commits[1], commits[2], commits[3], commits[4], commits[5],
        commits[6], commits[7], commits[8], commits[9], commits[10], commits[11]);
  }

  public List<Integer> getCommitTimes_Hour(String repo){
    try {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(new FileInputStream(repo + "/cth.txt"), StandardCharsets.UTF_8));
      String inputLine = null;
      List<Integer> commits = new ArrayList<>();
      while ((inputLine = in.readLine()) != null) {
        int a = Integer.parseInt(inputLine);
        commits.add(a);
      }
      return commits;
    } catch (Exception e) {
      e.printStackTrace();
    }
    int[] commits = new int[24];
    List<Date> commitTimes = commitRepository.findAllByRepoOrderByCommitdateAsc(repo.split("/")[1]).stream()
        .map(Commit::getCommitdate).toList();
    for (Date commitTime : commitTimes) {
      int a = commitTime.getHours();
      commits[a]++;
    }
    System.out.println(commits.toString());

    Path filePath = Paths.get(repo + "/cth.txt");
    try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
        StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
      for(int i=0;i<24;i++){
        writer.write(commits[i] + "\r\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return List.of(commits[0], commits[1], commits[2], commits[3], commits[4], commits[5],
        commits[6], commits[7], commits[8], commits[9], commits[10], commits[11], commits[12],
        commits[13], commits[14], commits[15], commits[16], commits[17], commits[18], commits[19], commits[20], commits[21], commits[22], commits[23]);
  }
  public void computeCommitBetweenReleases(String repo) {
    List<Commit> commits = commitRepository.findAllByRepoOrderByCommitdateAsc(repo);
    List<Release> releases = releaseRepository.findAllByRepoOrderByUpdatedAsc(repo);
    for (int i = 0; i < releases.size() - 1; i++) {
      long dif =
          releases.get(i + 1).getUpdated().getTime() - releases.get(i).getUpdated().getTime();
      int count = 0;
      for (int j = 0; j < commits.size(); j++) {
        long dif2 =
            commits.get(j).getCommitdate().getTime() - releases.get(i).getUpdated().getTime();
        if (dif2 >= 0 && dif2 <= dif) {
          count++;
        }
      }
      releases.get(i + 1).setCommits(count);
      releaseRepository.save(releases.get(i + 1));
    }
    System.out.println(repo + "计算完成" + releases.size());
  }

  public void analyzeContributors(String name, Repo repo) {
    int cnt = 1;
    int contributorNum = 0;
    try {
      while (true) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(name + "/contributors/" + cnt + ".json")));
        String inputLine = null;
        StringBuffer json = new StringBuffer("");
        while ((inputLine = in.readLine()) != null) {
          json.append(inputLine + "\n");
        }
//    System.out.println(json.toString());
        JSONArray jsona = (JSONArray) JSONArray.parse(json.toString());
        for (int i = 0; i < jsona.size(); i++) {
          JSONObject jsonObject = jsona.getJSONObject(i);
          Contributor contributor = new Contributor();
          contributor.setLogin(jsonObject.getString("login"));
          contributor.setContributions(jsonObject.getInteger("contributions"));
          contributor.setRepo(name.split("/")[1]);
          contributorRepository.save(contributor);
        }
//        System.out.println("Total Contributors:" + jsona.size());
        contributorNum += jsona.size();
        cnt++;
      }
    } catch (Exception e) {
      System.out.println("Have analyzed all contributors" + contributorNum);
      repo.setDeveloperNum(contributorNum);
      e.printStackTrace();
    }
  }

  public void analyzeIssues(String name, Repo repo) {
    int cnt = 1;
    int open_issueNum = 0;
    int closed_issueNum = 0;
    long[] solve_times = new long[100000];
    int solve_times_cnt = 0;
    long total = 0;
    try {
      while (true) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(name + "/issues/" + cnt + ".json")));
        String inputLine = null;
        StringBuffer json = new StringBuffer("");
        while ((inputLine = in.readLine()) != null) {
          json.append(inputLine + "\n");
        }
//    System.out.println(json.toString());
        JSONArray jsona = (JSONArray) JSONArray.parse(json.toString());
        for (int i = 0; i < jsona.size(); i++) {
          JSONObject jsonObject = jsona.getJSONObject(i);
          Issue issue = new Issue();
          issue.setRepo_name(name.split("/")[1]);
          issue.setId(jsonObject.getLong("id"));
          issue.setTitle(jsonObject.getString("title"));
          issue.setCreated_at(jsonObject.getDate("created_at"));
          issue.setUpdated_at(jsonObject.getDate("updated_at"));
          issue.setState(jsonObject.getString("state").equals("open"));
          long solve_time = issue.getUpdated_at().getTime() - issue.getCreated_at().getTime();
          issue.setSolve_time(solve_time);
          solve_times[solve_times_cnt++] = solve_time;
          total += solve_time;
          issueRepository.save(issue);
          if (issue.getState()) {
            open_issueNum++;
          } else {
            closed_issueNum++;
          }
        }
//        System.out.println("Total Issues:" + jsona.size());
        cnt++;
      }
    } catch (Exception e) {
      System.out.println(
          "Have analyzed all issues" + open_issueNum + " " + closed_issueNum + " " + (open_issueNum
              + closed_issueNum));
      long average = total / (open_issueNum + closed_issueNum);
      long min = Long.MAX_VALUE;
      long max = Long.MIN_VALUE;
      long var = 0;
      for (int i = 0; i < solve_times_cnt; i++) {
        if (solve_times[i] < min) {
          min = solve_times[i];
        }
        if (solve_times[i] > max) {
          max = solve_times[i];
        }
        //方差
        var += (solve_times[i] - average) / 1000l * (solve_times[i] - average) / 1000l;
      }
      var /= solve_times_cnt;
      repo.setIssue_variance(var);
      repo.setIssue_solve_average(average);
      repo.setIssue_extreme_value_diff(max - min);
      repo.setOpen_issues(open_issueNum);
      repo.setClose_issues(closed_issueNum);
      e.printStackTrace();
    }
  }

  public void analyzeReleases(String name, Repo repo) {
    int cnt = 1;
    int releaseNum = 0;
    try {
      while (true) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(name + "/releases/" + cnt + ".json")));
        String inputLine = null;
        StringBuffer json = new StringBuffer("");
        while ((inputLine = in.readLine()) != null) {
          json.append(inputLine + "\n");
        }
//    System.out.println(json.toString());
        JSONArray jsona = (JSONArray) JSONArray.parse(json.toString());
        for (int i = 0; i < jsona.size(); i++) {
          JSONObject jsonObject = jsona.getJSONObject(i);
          Release release = new Release();
          release.setRepo(name.split("/")[1]);
          release.setId(jsonObject.getLong("id"));
          release.setUpdated(jsonObject.getDate("published_at"));
          release.setCreated_at(jsonObject.getDate("created_at"));
          release.setName(jsonObject.getString("name"));
          releaseRepository.save(release);
        }
        releaseNum += jsona.size();
//        System.out.println("Total Releases:" + jsona.size());
        cnt++;
      }
    } catch (Exception e) {
      System.out.println("Have analyzed all releases" + releaseNum);
      repo.setReleases_num(releaseNum);
      e.printStackTrace();
    }
  }

  public void analyzeCommits(String name, Repo repo) {
    int cnt = 1;
    int commitNum = 0;
    try {
      while (true) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(new FileInputStream(name + "/commits/" + cnt + ".json")));
        String inputLine = null;
        StringBuffer json = new StringBuffer("");
        while ((inputLine = in.readLine()) != null) {
          json.append(inputLine + "\n");
        }
//    System.out.println(json.toString());
        JSONArray jsona = (JSONArray) JSONArray.parse(json.toString());
        for (int i = 0; i < jsona.size(); i++) {
          JSONObject jsonObject = jsona.getJSONObject(i);
          Commit commit = new Commit();
          commit.setId(jsonObject.getLong("id"));
          commit.setRepo(name.split("/")[1]);
          commit.setSha(jsonObject.getString("sha"));
          commit.setCommitdate(
              jsonObject.getJSONObject("commit").getJSONObject("author").getDate("date"));
          JSONObject committer = jsonObject.getJSONObject("committer");
          if (committer != null) {
            commit.setLogin(committer.getString("login"));
          }
          commitRepository.save(commit);
        }
        commitNum += jsona.size();
//        System.out.println("Total Commits:" + commitNum);
        cnt++;
      }
    } catch (Exception e) {
      System.out.println("Have analyzed all commits" + commitNum);
      repo.setCommit_times(commitNum);
      e.printStackTrace();
    }
  }

  //  public void addRepos() {
//
//
//    Repo[] repos=new Repo[2];
//    for (int i = 0; i <2 ; i++) {
//      try {
//        repos[i]=new Repo();
//        String repo1 = "SpringBoot";
//        if(i==1) repo1="Vscode";
//        BufferedReader in = new BufferedReader(
//            new InputStreamReader(new FileInputStream("raw_data/" + repo1 + "/contributors.json")));
//        String inputLine = null;
//        StringBuffer json = new StringBuffer("");
//        while ((inputLine = in.readLine()) != null) {
//          json.append(inputLine + "\n");
//
//        }
////    System.out.println(json.toString());
//        JSONArray jsona = (JSONArray) JSONArray.parse(json.toString());
//        JSONObject object = JSONObject.parseObject(jsona.get(0).toString());
//        System.out.println("Total Contributors:" + jsona.size());
//        System.out.println("Contributor:" + object.get("login").toString());
//        repos[i].setDeveloperNum(jsona.size());
//
////next is all_issues.json
//        in = new BufferedReader(
//            new InputStreamReader(new FileInputStream("raw_data/" + repo1 + "/issues.json")));
//        json = new StringBuffer("");
//        while ((inputLine = in.readLine()) != null) {
//          json.append(inputLine + "\n");
//        }
////    System.out.println(json.toString());
//        jsona = (JSONArray) JSONArray.parse(json.toString());
////    JSONObject object2 = JSONObject.parseObject(jsona.get(0).toString());
//
//        int open_issues = 0;
//        int closed_issues = 0;
//        long solved_time = 0;
//        long max_solved_time = 0;
//        long min_solved_time = Long.MAX_VALUE;
//        long[] arr = new long[jsona.size()];
//        int cnt = 0;
//        for (JSONObject object2 : jsona.toJavaList(JSONObject.class)) {
//
//          System.out.println("Issue:" + object2.get("state").toString());
//          if (object2.get("state").toString().equals("open")) {
//            open_issues++;
//          } else {
//            closed_issues++;
//          }
//
//          String update_time = object2.get("updated_at").toString();
////      Date update_time=(Date) object2.get("updated_at");
//          String create_time = object2.get("created_at").toString();
////      Date create_time=(Date) object2.get("created_at");
//          Date date = null;
//          Date date2 = null;
//          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
//          date = formatter.parse(update_time);
//          System.out.println("Update Time:" + date.toString());
//          date2 = formatter.parse(create_time);
//          System.out.println("Create Time:" + date2.toString());
//          repos[i].setUpdated_at(date);
//          repos[i].setCreated_at(date2);
//
////        long nd = 1000 * 24 * 60 * 60;
////        long nh = 1000 * 60 * 60;
////        long nm = 1000 * 60;
//          // long ns = 1000;
//          // 获得两个时间的毫秒时间差异
//          long diff = date.getTime() - date2.getTime();
//          if (diff > max_solved_time) {
//            max_solved_time = diff;
//          }
//          if (diff < min_solved_time) {
//            min_solved_time = diff;
//          }
//          solved_time += diff;
//          arr[cnt++] = diff;
//          // 计算差多少天
////        long day = diff / nd;
////        // 计算差多少小时
////        long hour = diff % nd / nh;
////        // 计算差多少分钟
////        long min = diff % nd % nh / nm;
////        // 计算差多少秒//输出结果
////        // long sec = diff % nd % nh % nm / ns;
////        System.out.println(day + "天" + hour + "小时" + min + "分钟");
////        System.out.println(date);
//        }
//        System.out.println("Open Issues:" + open_issues);
//        System.out.println("Closed Issues:" + closed_issues);
//        repos[i].setOpen_issues(open_issues);
//        repos[i].setClose_issues(closed_issues);
//        repos[i].setIssue_solve_average(solved_time / jsona.size());
//        repos[i].setIssue_extreme_value_diff(max_solved_time - min_solved_time);
//
//        double sum = 0;
//        for (int k = 0; k < arr.length; k++) {
//          sum += arr[k];
//          System.out.print(arr[k] + " ");
//
//        }
//        double avg = sum / arr.length;
//        System.out.println("平均值是：" + avg);
//        double variance = 0;
//        for (int j = 0; j < arr.length; j++) {
//          variance += (arr[j] - avg) * (arr[j] - avg);
//        }
//        double f = variance / arr.length;
//        System.out.println("数组方差是：" + f);
//        repos[i].setIssue_variance(f);
//
//        //next is commits.json
//        in = new BufferedReader(
//            new InputStreamReader(new FileInputStream("raw_data/" + repo1 + "/commits.json")));
//        json = new StringBuffer("");
//        while ((inputLine = in.readLine()) != null) {
//          json.append(inputLine + "\n");
//        }
////    System.out.println(json.toString());
//        jsona = (JSONArray) JSONArray.parse(json.toString());
//        repos[i].setCommit_times(jsona.size());
////      JSONObject object1 = JSONObject.parseObject(jsona.get(0).toString());
//        System.out.println("fuck");
//
//        //next is releases.json
//        in = new BufferedReader(
//            new InputStreamReader(new FileInputStream("raw_data/" + repo1 + "/releases.json")));
//        json = new StringBuffer("");
//        while ((inputLine = in.readLine()) != null) {
//          json.append(inputLine + "\n");
//        }
////    System.out.println(json.toString());
//        jsona = (JSONArray) JSONArray.parse(json.toString());
//        repos[i].setReleases_num(jsona.size());
////    JSONObject object3 = JSONObject.parseObject(jsona.get(0).toString());
//
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//
//    repoRepository.saveAll(List.of(repos[0], repos[1]));
//  }
  public void ReadLocalData(String name) {
//    String name="spring-projects/spring-boot";
    Repo repo = new Repo();
    repo.setName(name.split("/")[1]);
    analyzeContributors(name, repo);
    analyzeCommits(name, repo);
    analyzeIssues(name, repo);
    analyzeReleases(name, repo);
    repoRepository.save(repo);
  }

  public void addRepo(Repo repo) {
    repoRepository.save(repo);
  }

  public List<Commit> findByLoginLike(String s) {
    return commitRepository.findByLoginLike(s);
  }

  public List<Commit> getCommits() {
    return commitRepository.findAll();
  }

  public List<Release> getReleases() {
    return releaseRepository.findAll();
  }



  public List<Release> findByCommitsMoreThan(Integer integer) {
    return releaseRepository.findByCommitsGreaterThan(integer);
  }
}
