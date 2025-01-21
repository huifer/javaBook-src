package org.huifer.rbac.service;

import org.huifer.rbac.entity.db.Demo;
import org.huifer.rbac.mapper.DemoMapper;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    final
    DemoMapper demoMapper;

    public DemoService(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }


    public void lgs() {
        Demo demo = new Demo();
        demo.setName("zs");
        demoMapper.insert(demo);
    }

    public void update() {
        Demo demo = demoMapper.selectById(2L);
        demo.setName("aklsfj");
        demoMapper.updateById(demo);

    }

    public void delete() {
        demoMapper.deleteById(2L);
    }
}
