package com.huifer.idgen.my.service.populator;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wang
 * @description:
 */
public class LockIdPropulator extends BasePopulator {

	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void populatorId(Id id, IdMeta idMeta) {
		lock.lock();
		try {
			super.populatorId(id, idMeta);
		} finally {
			lock.unlock();
		}
	}


}