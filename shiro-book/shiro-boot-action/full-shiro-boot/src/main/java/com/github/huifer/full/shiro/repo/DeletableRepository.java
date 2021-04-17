package com.github.huifer.full.shiro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface DeletableRepository<T, ID>
    extends JpaRepository<T, ID>,
    JpaSpecificationExecutor<T>,
    PagingAndSortingRepository<T, ID> {

}