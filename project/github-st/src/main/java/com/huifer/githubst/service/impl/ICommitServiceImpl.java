package com.huifer.githubst.service.impl;

import com.huifer.githubst.beans.GitHubUserInfo;
import com.huifer.githubst.entity.CommitInfo;
import com.huifer.githubst.mapper.CommitInfoMapper;
import com.huifer.githubst.service.ICommitService;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ICommitServiceImpl implements ICommitService {
    static List<String> repoList = new ArrayList<>();

    static {
        repoList.add("javaBook-src");
        repoList.add("spring-framework-read\n");
        repoList.add("source-code-hunter");
        repoList.add("record_oneself");
    }

    @Autowired
    private GitHubUserInfo gitHubUserInfo;

    @Autowired
    private CommitInfoMapper commitInfoMapper;

    @Override
    public void getCommit() throws IOException {
        GitHubClient client = new GitHubClient();
        client.setCredentials(gitHubUserInfo.getUsername(), gitHubUserInfo.getPassword());
        RepositoryService repositoryService = new RepositoryService();

        CommitService commitService = new CommitService(client);
        for (Repository repository : repositoryService.getRepositories("huifer")) {
            System.out.println("当前仓库=" + repository.getName());

            if (repoList.contains(repository.getName())) {

                int size = 25;
                for (Collection<RepositoryCommit> commits : commitService.pageCommits(repository, size)) {
                    for (RepositoryCommit commit : commits) {
                        CommitInfo commitInfo = new CommitInfo();
                        commitInfo.setSha(commit.getSha());
                        commitInfo.setUrl(commit.getUrl());
                        commitInfo.setMessage(commit.getCommit().getMessage());
                        commitInfo.setName(commit.getCommit().getCommitter().getName());
                        commitInfo.setEmail(commit.getCommit().getCommitter().getEmail());
                        commitInfo.setDate(commit.getCommit().getCommitter().getDate());
                        commitInfo.setRepoName(repository.getName());
                        if (commit.getStats() != null) {

                            commitInfo.setAdditions(commit.getStats().getAdditions());
                            commitInfo.setDeletions(commit.getStats().getDeletions());
                            commitInfo.setTotal(commit.getStats().getTotal());
                        } else {
                            commitInfo.setAdditions(0);
                            commitInfo.setDeletions(0);
                            commitInfo.setTotal(0);
                        }
                        try {
                            this.commitInfoMapper.insert(commitInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        }


        System.out.println();

    }
}
