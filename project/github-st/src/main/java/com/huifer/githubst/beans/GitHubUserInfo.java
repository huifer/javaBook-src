package com.huifer.githubst.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class GitHubUserInfo {
    @Value("${github.username}")
    private String username;
    @Value("${github.password}")
    private String password;
}
