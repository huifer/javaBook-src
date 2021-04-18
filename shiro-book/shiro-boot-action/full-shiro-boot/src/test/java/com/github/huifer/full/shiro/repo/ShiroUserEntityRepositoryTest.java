package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.FullShiroApp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@SpringBootTest(classes = {FullShiroApp.class})
class ShiroUserEntityRepositoryTest {

  @Autowired
  private ShiroUserEntityRepository shiroUserEntityRepository;

  @Test
  void findShiroUserEntityByLoginName() {
  }

  @Test
  void queryAllByEmailAndUsernameAndGender() {
    PageRequest pageable = PageRequest.of(1, 1);
    Page<ShiroUserEntity> all = shiroUserEntityRepository
        .findAll(new Specification<ShiroUserEntity>() {
          @Override
          public Predicate toPredicate(
              Root<ShiroUserEntity> root,
              CriteriaQuery<?> query,
              CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            list.add(criteriaBuilder.like(root.get("username"), "%%"));
            return null;
          }
        }, pageable);
    System.out.println();
  }
}