package com.huifer.githubst.controller;

import com.huifer.githubst.service.ICommitService;
import com.huifer.githubst.service.IRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private IRepoService iRepoService;
    @Autowired
    private ICommitService iCommitService;

    @GetMapping("/repo")
    public void repo() throws IOException {
        iRepoService.getGitHubRepoInfo();

    }

    @GetMapping("/commit")
    public void commit() throws IOException {
        iCommitService.getCommit();

    }
}
