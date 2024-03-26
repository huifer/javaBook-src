package com.github.huifer.simple.shiro.boot.repo;

import static org.junit.Assert.*;

import com.github.huifer.simple.shiro.boot.ShiroApp;
import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author huifer
 */
@SpringBootTest(classes = {ShiroApp.class})
public class ShiroUserRepoTest {

  @Autowired
  private ShiroUserRepo userRepo;

  @Test
  public void testFindShiroUserEntityByUsername(){
    Optional<ShiroUserEntity> admin = userRepo.findShiroUserEntityByUsername("admin");
    if (admin.isPresent()) {
      System.out.println(admin.get());
    }
  }
}