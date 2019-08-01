package com.huifer.idgen.my.service.populator;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;

/**
 * @author: wang
 * @description:
 */
public class SyncIdPopulator extends BasePopulator {

	@Override
	public synchronized void populatorId(Id id, IdMeta idMeta) {
		super.populatorId(id, idMeta);
	}
}