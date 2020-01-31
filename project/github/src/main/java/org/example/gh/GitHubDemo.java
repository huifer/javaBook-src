package org.example.gh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.alibaba.fastjson.JSON;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 * token 限制
 */


public class GitHubDemo {
    public static void main(String[] args) throws Exception {
        GitHubClient client = new GitHubClient();
        client.setCredentials("xxxx", "xxx");
        RepositoryService repositoryService = new RepositoryService();

        CommitService commitService = new CommitService(client);
        String yearMonth = getYearMonth();
        List<CountRepoResult> results = new ArrayList<CountRepoResult>();
        for (Repository repository : repositoryService.getRepositories("huifer")) {
            System.out.println("当前仓库=" + repository.getName());
//        Repository repository = repositoryService.getRepository("huifer", "spring-framework-read");
            CountRepoResult countRepoResult = new CountRepoResult();
            int size = 25;
            List<CommitsInfo> commitsInfos = new ArrayList<CommitsInfo>();
            for (Collection<RepositoryCommit> commits : commitService.pageCommits(repository,
                    size)) {
                for (RepositoryCommit commit : commits) {
                    String sha = commit.getSha().substring(0, 7);
                    String author = commit.getCommit().getAuthor().getName();
                    Date date = commit.getCommit().getAuthor().getDate();
                    String message = commit.getCommit().getMessage();
                    CommitsInfo commitsInfo = new CommitsInfo();
                    commitsInfo.setSha(sha);
                    commitsInfo.setAuthor(author);
                    commitsInfo.setCreateTime(date);
                    commitsInfo.setMesg(message);
                    commitsInfos.add(commitsInfo);
                }
            }
            write(yearMonth + "-info-" + repository.getName(), JSON.toJSONString(commitsInfos));
            countRepoResult.setUrl(repository.getCloneUrl());
            countRepoResult.setDate(yearMonth);
            countRepoResult.setRepoName(repository.getName());
            countRepoResult.setCount(commitsInfos.size());
            write(yearMonth + "-result-" + repository.getName(), JSON.toJSONString(countRepoResult));
            results.add(countRepoResult);
        }
        write(yearMonth, JSON.toJSONString(results));


        System.out.println();
    }
//    public static void main(String[] args) throws IOException {
//        String user = "huifer";
//        List<Repository> repositories = getUserRepoList(user);
//        System.out.println("仓库获取完成");
//        List<CountRepoResult> countRepoResults = new ArrayList<CountRepoResult>();
//        for (Repository repository : repositories) {
//            String repoName = repository.getName();
//            RepoCommitsInfo repoCommitsInfo = getOneRepoCommits(user, repoName);
//            System.out.println("当前仓库提交信息获取完成");
//            int countOneRepo = countOneRepo(repoCommitsInfo);
//            System.out.println("开始组装结果集合");
//            CountRepoResult countRepoResult = new CountRepoResult();
//            countRepoResult.setDate(getYearMonth());
//            countRepoResult.setUrl(repository.getCloneUrl());
//            countRepoResult.setRepoName(repoName);
//            countRepoResult.setCount(countOneRepo);
//            System.out.println(countRepoResult);
//            countRepoResults.add(countRepoResult);
//        }
//        if (!countRepoResults.isEmpty()) {
//            String s = JSON.toJSONString(countRepoResults);
//            write(countRepoResults.get(0).getDate(), s);
//        }
//    }

    private static void write(String path, String content) {
        try {
            File file = new File(path + ".json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            fileWritter.write(content);
            fileWritter.close();
            System.out.println("finish");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getYearMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "年" + month + "月";
    }

    /**
     * 统计,每个月提交数量
     * @param repoCommitsInfo 仓库提交信息
     */
    private static int countOneRepo(RepoCommitsInfo repoCommitsInfo) {

        List<CommitsInfo> commitsInfoList = repoCommitsInfo.getCommitsInfoList();
        Long monthEndTime = getMonthEndTime(System.currentTimeMillis(), "GMT+8:00");
        Long monthStartTime = getMonthStartTime(System.currentTimeMillis(), "GMT+8:00");
        int count = 0;
        for (CommitsInfo commitsInfo : commitsInfoList) {
            Date createTime = commitsInfo.getCreateTime();
            long createTimeTime = createTime.getTime();
            // 创建时间小于等于当月结束时间,创建时间大于等于当月开始时间
            if (monthStartTime <= createTimeTime || createTimeTime <= monthEndTime) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取当月开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthStartTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月的结束时间戳
     *
     * @param timeStamp 当前毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthEndTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 单个仓库个人提交数量记录
     * @param userName 用户名
     * @param repoName 仓库名
     * @return
     */
    private static RepoCommitsInfo getOneRepoCommits(String userName, String repoName) {
        final int size = 25;
        final RepositoryId repo = new RepositoryId(userName, repoName);
        final CommitService service = new CommitService();
        RepoCommitsInfo repoCommitsInfo = new RepoCommitsInfo();
        repoCommitsInfo.setRepoName(repoName);

        List<CommitsInfo> commitsInfos = new ArrayList<CommitsInfo>();
        for (Collection<RepositoryCommit> commits : service.pageCommits(repo,
                size)) {
            for (RepositoryCommit commit : commits) {
                String sha = commit.getSha().substring(0, 7);
                String author = commit.getCommit().getAuthor().getName();
                Date date = commit.getCommit().getAuthor().getDate();
                if (author.equalsIgnoreCase(userName)) {
                    CommitsInfo commitsInfo = new CommitsInfo(sha, author, date);
                    commitsInfos.add(commitsInfo);
                }
            }
        }
        repoCommitsInfo.setCommitsInfoList(commitsInfos);
        return repoCommitsInfo;
    }

    /**
     * 获取仓库信息
     * @param userName 用户名
     * @return
     * @throws IOException
     */
    private static List<Repository> getUserRepoList(String userName) throws IOException {
        RepositoryService service = new RepositoryService();
        return new ArrayList<Repository>(service.getRepositories(userName));
    }

    private static class CountRepoResult {
        private String date;

        private String url;

        private String repoName;

        private int count;

        public CountRepoResult() {
        }

        public CountRepoResult(String date, String repoName, int count) {
            this.date = date;
            this.repoName = repoName;
            this.count = count;
        }

        @Override
        public String toString() {
            return "CountRepoResult{" +
                    "date='" + date + '\'' +
                    ", url='" + url + '\'' +
                    ", repoName='" + repoName + '\'' +
                    ", count=" + count +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRepoName() {
            return repoName;
        }

        public void setRepoName(String repoName) {
            this.repoName = repoName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    private static class RepoCommitsInfo {
        private String repoName;


        private List<CommitsInfo> commitsInfoList;

        public RepoCommitsInfo(String repoName, List<CommitsInfo> commitsInfoList) {
            this.repoName = repoName;
            this.commitsInfoList = commitsInfoList;
        }

        public RepoCommitsInfo() {
        }

        public String getRepoName() {
            return repoName;
        }

        public void setRepoName(String repoName) {
            this.repoName = repoName;
        }

        public List<CommitsInfo> getCommitsInfoList() {
            return commitsInfoList;
        }

        public void setCommitsInfoList(List<CommitsInfo> commitsInfoList) {
            this.commitsInfoList = commitsInfoList;
        }

        @Override
        public String toString() {
            return "RepoCommitsInfo{" +
                    "repoName='" + repoName + '\'' +
                    ", commitsInfoList=" + commitsInfoList +
                    '}';
        }
    }

    private static class CommitsInfo {
        private String sha;

        private String author;

        private Date createTime;

        private String mesg;

        public CommitsInfo() {
        }

        public CommitsInfo(String sha, String author, Date createTime) {
            this.sha = sha;
            this.author = author;
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "CommitsInfo{" +
                    "sha='" + sha + '\'' +
                    ", author='" + author + '\'' +
                    ", createTime=" + createTime +
                    ", mesg='" + mesg + '\'' +
                    '}';
        }

        public String getMesg() {
            return mesg;
        }

        public void setMesg(String mesg) {
            this.mesg = mesg;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }

}

