package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroCompany;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 企业表(ShiroCompany)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:17
 */
public interface ShiroCompanyDao extends BaseMapper<ShiroCompany> {

  List<ShiroCompany> findByUserId(@Param("userId") int userId);

}
