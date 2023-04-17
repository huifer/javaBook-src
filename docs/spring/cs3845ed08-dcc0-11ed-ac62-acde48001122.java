package org.huifer.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.rbac.entity.db.Demo;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
}