package org.huifer.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.rbac.entity.db.TUser;

@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
}