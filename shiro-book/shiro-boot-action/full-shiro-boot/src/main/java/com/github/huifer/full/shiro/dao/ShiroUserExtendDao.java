package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroUserExtend;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户拓展信息表(ShiroUserExtend)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:22
 */
public interface ShiroUserExtendDao extends BaseMapper<ShiroUserExtend> {

  List<ShiroUserExtend> findByUserId(@Param("userId") int userId);
}
