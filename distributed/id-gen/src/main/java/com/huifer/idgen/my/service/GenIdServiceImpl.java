package com.huifer.idgen.my.service;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.enums.IdType;
import com.huifer.idgen.my.service.populator.AtomicIdPopulator;
import com.huifer.idgen.my.service.populator.IdPopulator;
import com.huifer.idgen.my.service.populator.LockIdPropulator;
import com.huifer.idgen.my.service.populator.SyncIdPopulator;
import com.huifer.idgen.my.service.util.CommonUtils;

/**
 * @author: wang
 * @description:
 */
public class GenIdServiceImpl extends AbstractIdService {

	public static final String sync_lock = "lock";
	public static final String atomic = "atomic";

	protected IdPopulator idPopulator;

	public GenIdServiceImpl() {
		super();
		initPopulator();
	}

	public GenIdServiceImpl(IdType idType) {
		super(idType);
		initPopulator();
	}

	public GenIdServiceImpl(String idType) {
		super(idType);
		initPopulator();
	}


	public void initPopulator() {
		if (idPopulator != null) {

		} else if (CommonUtils.isPropKeyOn(sync_lock)) {
			idPopulator = new SyncIdPopulator();
		} else if (CommonUtils.isPropKeyOn(atomic)) {
			idPopulator = new AtomicIdPopulator();
		} else {
			idPopulator = new LockIdPropulator();
		}
	}

	@Override
	protected void populateId(Id id) {
		idPopulator.populatorId(id, this.idMeta);
	}

	public void setIdPopulator(IdPopulator idPopulator) {
		this.idPopulator = idPopulator;
	}
}