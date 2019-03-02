package com.huifer.mybatis.dao;

import com.huifer.mybatis.pojo.Dept;

/**
 * 描述:
 * deptMapper
 *
 * @author huifer
 * @date 2019-02-24
 */
public interface DeptMapper {

    Dept deptFindById(Long deptno);

    Dept deptFindByIdWithEmp(Long deptno);



}
