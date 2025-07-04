package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.ShiroApp;
import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author huifer
 */
@SpringBootTest(classes = {ShiroApp.class})
public class UserBindRoleRepoTest {

  @Autowired
  private ShiroRoleRepo shiroRoleRepo;

  @Test
  public void queryByUserId() {
    List<ShiroRoleEntity> shiroRoleEntities = shiroRoleRepo.queryByUserId(1);
    System.out.println();
  }

}