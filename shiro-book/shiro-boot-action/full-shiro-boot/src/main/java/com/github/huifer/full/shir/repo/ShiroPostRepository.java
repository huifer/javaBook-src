package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroPostRepository extends JpaRepository<ShiroPost, Integer>,
    JpaSpecificationExecutor<ShiroPost> {

}