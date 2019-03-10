package com.huifer.springmybatis.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 描述:
 * 转账
 *
 * @author huifer
 * @date 2019-03-10
 */
public interface AccountMapper {
    /**
     * 更新记录
     *
     * @param name
     * @param money
     */
    void update(@Param("name") String name, @Param("money") double money);

    /**
     * 查询记录
     *
     * @param name
     * @return
     */
    double queryMoney(@Param("name")String name);
}
