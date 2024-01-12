package com.huifer.design.template;

import java.sql.ResultSet;

/**
 * <p>Title : RowMapper </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws Exception;


}
