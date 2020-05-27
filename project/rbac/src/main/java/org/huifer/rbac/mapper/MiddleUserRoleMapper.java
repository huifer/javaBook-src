package org.huifer.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.rbac.entity.db.MiddleUserRole;

@Mapper
public interface MiddleUserRoleMapper extends BaseMapper<MiddleUserRole> {
}