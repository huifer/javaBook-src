package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.BaseEntry;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class DeletableRepositoryImpl<T extends BaseEntry, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> implements
    DeletableRepository<T, ID> {

  private final EntityManager entityManager;

  public DeletableRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  public DeletableRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
    this.entityManager = em;
  }

  @Override
  public <S extends T> S save(S entity) {
    entity.setDeleted(0);
    if (entity.getCreateTime() == null) {
      entity.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
    }
    entity.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
    return super.save(entity);
  }

  @Override
  public void deleteById(ID s) {
    boolean notFound = true;

    Optional<T> opt = this.findById(s);
    T entity = null;
    if (opt != null) {
      entity = opt.get();
      if (entity != null) {
        notFound = false;

        entity.setDeleted(1);
        entity.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        this.save(entity);
      }
    }

    //如果没找到对应主键的实体，则抛出EntityNotFoundException异常
    if (notFound == true) {
      throw new EntityNotFoundException(
          "对应主键为\"" + s + "\"的[" + (entity == null ? "未知实体" : entity.getClass().getSimpleName())
              + "]实体没有查询到！");
    }
  }

  @Override
  public void delete(T entity) {
    entity.setDeleted(1);
    entity.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
    this.save(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends T> iterable) {
    Iterator iterator = iterable.iterator();
    while (iterator.hasNext()) {
      T entity = (T) iterator.next();
      entity.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
      entity.setDeleted(1);
    }

    this.saveAll(iterable);
  }

  @Override
  public void deleteAll() {
    Iterable<T> allList = this.findAll();

    this.deleteAll(allList);
  }
}