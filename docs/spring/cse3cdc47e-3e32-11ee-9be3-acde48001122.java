package com.github.huifer.cache.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  @Cacheable("demo")
  public String find() {
    return "1";
  }

  @CachePut("demo")
  public String update(Integer id) {
    return String.valueOf(id);
  }

}
