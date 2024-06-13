package org.huifer.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.rbac.entity.db.TMenu;

@Mapper
public interface TMenuMapper extends BaseMapper<TMenu> {
}