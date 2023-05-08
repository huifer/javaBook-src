package com.github.huifer.simple.shiro.boot.repo;

import static org.junit.Assert.*;

import com.github.huifer.simple.shiro.boot.ShiroApp;
import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author huifer
 */
@SpringBootTest(classes = {ShiroApp.class})
public class ShiroRoleRepoTest {

  @Autowired
  private ShiroRoleRepo roleRepo;
  @Test
  public void findByUserId(){

  }

}