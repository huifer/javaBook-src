package com.huifer.githubst.service.impl;

import java.util.Date;

import com.huifer.githubst.beans.GitHubUserInfo;
import com.huifer.githubst.entity.RepoInfo;
import com.huifer.githubst.mapper.RepoInfoMapper;
import com.huifer.githubst.service.IRepoService;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IRepoServiceImpl implements IRepoService {
    @Autowired
    private GitHubUserInfo gitHubUserInfo;
    @Autowired
    private RepoInfoMapper repoInfoMapper;

    /**
     * 仓库信息
     * @throws IOException
     */
    @Override
    public void getGitHubRepoInfo() throws IOException {
        GitHubClient client = new GitHubClient();
        client.setCredentials(gitHubUserInfo.getUsername(), gitHubUserInfo.getPassword());
        RepositoryService repositoryService = new RepositoryService();
        for (Repository repository : repositoryService.getRepositories(gitHubUserInfo.getUsername())) {
            RepoInfo repoInfo = new RepoInfo();
            repoInfo.setCreatedAt(repository.getCreatedAt());
            repoInfo.setPushedAt(repository.getPushedAt());
            repoInfo.setUpdatedAt(repository.getUpdatedAt());
            repoInfo.setForks(repository.getForks());
            repoInfo.setSize(repository.getSize());
            repoInfo.setWatchers(repository.getWatchers());
            repoInfo.setCloneUrl(repository.getCloneUrl());
            repoInfo.setDescription(repository.getDescription());
            repoInfo.setHomepage(repository.getHomepage());
            repoInfo.setGitUrl(repository.getGitUrl());
            repoInfo.setHtmlUrl(repository.getHtmlUrl());
            repoInfo.setLanguage(repository.getLanguage());
            repoInfo.setMasterBranch(repository.getMasterBranch());
            repoInfo.setMirrorUrl(repository.getMirrorUrl());
            repoInfo.setName(repository.getName());
            repoInfo.setSshUrl(repository.getSshUrl());
            repoInfo.setSvnUrl(repository.getSvnUrl());
            repoInfo.setUrl(repository.getUrl());
            repoInfoMapper.insert(repoInfo);
        }
    }
}
