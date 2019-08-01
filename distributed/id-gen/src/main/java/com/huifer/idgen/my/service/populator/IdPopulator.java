package com.huifer.idgen.my.service.populator;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;

/**
 * @author: wang
 * @description:
 */
public interface IdPopulator {

	/**
	 * 雪花算法的细化
	 *
	 * @param id
	 * @param idMeta
	 */
	void populatorId(Id id, IdMeta idMeta);

}