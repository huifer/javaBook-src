package org.huifer.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.rbac.entity.db.TRole;

@Mapper
public interface TRoleMapper extends BaseMapper<TRole> {
}