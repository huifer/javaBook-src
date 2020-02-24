package com.huifer.githubst.entity;

import java.util.Date;
import lombok.Data;

@Data
public class RepoInfo {
    private Integer id;

    private Date createdAt;

    private Date pushedAt;

    private Date updatedAt;

    private Integer forks;

    private Integer size;

    private Integer watchers;

    private String cloneUrl;

    private String description;

    private String homepage;

    private String gitUrl;

    private String htmlUrl;

    private String language;

    private String masterBranch;

    private String mirrorUrl;

    private String name;

    private String sshUrl;

    private String svnUrl;

    private String url;
}