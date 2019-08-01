package com.huifer.idgen.my.service;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;
import com.huifer.idgen.my.service.bean.enums.IdType;
import com.huifer.idgen.my.service.conv.IdConverter;
import com.huifer.idgen.my.service.conv.IdConverterImpl;
import com.huifer.idgen.my.service.factory.IdMetaFactory;
import com.huifer.idgen.my.service.provider.MachineIdProvider;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang
 * @description: Id生产策略父类
 */
@Slf4j
public abstract class AbstractIdService implements GenIdService {


	protected long machineId = -1;
	protected long genMethod = 0;
	protected long type = 0;
	protected long version = 0;

	protected IdMeta idMeta;
	protected IdType idType;
	protected IdConverter idConverter;
	protected MachineIdProvider machineIdProvider;

	public AbstractIdService() {
		this.idType = IdType.TYPE_ONE;
	}

	public AbstractIdService(String type) {
		this.idType = IdType.parse(type);
	}

	public AbstractIdService(IdType type) {
		this.idType = type;
	}


	public void init() {
		this.machineId = machineIdProvider.getMachineId();
		if (this.machineId < 0) {
			throw new IllegalArgumentException("machinId > 0 ");
		}
		if (this.idMeta == null) {
			this.idMeta = IdMetaFactory.getIdMeta(this.idType);
			this.type = this.idType.value();
		} else {
			if (this.idMeta.getTimeBits() == 30) {
				this.type = 1L;
			} else if (this.idMeta.getTimeBits() == 40) {
				this.type = 0L;
			} else {
				throw new RuntimeException("时间位在30-40之间");
			}
		}
		this.idConverter = new IdConverterImpl(this.idMeta);
	}

	protected abstract void populateId(Id id);

	public long genId() {
		Id id = new Id();
		id.setMachine(machineId);
		id.setGenMethod(genMethod);
		id.setType(type);
		id.setVersion(version);
		populateId(id);
		long res = idConverter.converter(id);
		log.info("id={}-->{}", id, res);
		return res;
	}

	public Id expId(long id) {
		return idConverter.converter(id);
	}

	public long makeId(long time, long seq) {
		return makeId(time, seq, machineId);
	}

	public long makeId(long time, long seq, long machine) {
		return makeId(genMethod, time, seq, machine);
	}

	public long makeId(long genMethod, long time, long seq, long machine) {
		return makeId(type, genMethod, time, seq, machine);
	}

	public long makeId(long type, long genMethod, long time, long seq, long machine) {
		return makeId(version, type, genMethod, time, seq, machine);
	}

	public long makeId(long version, long type, long genMethod, long time, long seq, long machine) {
		IdType idType = IdType.parse(type);
		Id id = new Id(machine, seq, time, genMethod, type, version);
		IdConverter idConverter = new IdConverterImpl(idType);
		return idConverter.converter(id);
	}

	public Date transTime(long time) {
		if (idType == IdType.TYPE_ONE) {
			return new Date(time * 1000 + 1420041600000L);
		} else if (idType == IdType.TYPE_TWO) {
			return new Date(time + 1420041600000L);
		}
		return null;
	}

	public void setMachineId(long machineId) {
		this.machineId = machineId;
	}

	public void setGenMethod(long genMethod) {
		this.genMethod = genMethod;
	}

	public void setType(long type) {
		this.type = type;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void setIdMeta(IdMeta idMeta) {
		this.idMeta = idMeta;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public void setIdConverter(IdConverter idConverter) {
		this.idConverter = idConverter;
	}

	public void setMachineIdProvider(MachineIdProvider machineIdProvider) {
		this.machineIdProvider = machineIdProvider;
	}
}