package com.huifer.githubst.entity;


import java.util.Date;
import lombok.Data;

@Data
public class CommitInfo {
    private Integer id;

    private String sha;

    private String url;

    private String message;

    private String name;

    private String email;

    private Date date;

    private Integer additions;

    private Integer deletions;

    private Integer total;
    private String repoName;

    @Override
    public String toString() {
        return "CommitInfo{" +
                "id=" + id +
                ", sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", additions=" + additions +
                ", deletions=" + deletions +
                ", total=" + total +
                ", repoName='" + repoName + '\'' +
                '}';
    }
}