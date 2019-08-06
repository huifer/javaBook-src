package com.huifer.idgen.my.service.conv;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;
import com.huifer.idgen.my.service.factory.IdMetaFactory;
import com.huifer.idgen.my.service.bean.enums.IdType;

/**
 * @author: wang
 * @description:
 */
public class IdConverterImpl implements IdConverter {

    private IdMeta idMeta;

    public IdConverterImpl(IdType idType) {
        this(IdMetaFactory.getIdMeta(idType));
    }

    public IdConverterImpl(IdMeta idMeta) {
        this.idMeta = idMeta;
    }


    @Override
    public long converter(Id id) {
        return doConverter(id, this.idMeta);
    }

    @Override
    public Id converter(long id) {
        return doConvert(id, idMeta);
    }

	/**
	 * 根据id生产,根据id元数据生产一个long类型的id
	 * @param id
	 * @param idMeta
	 * @return
	 */
    protected long doConverter(Id id, IdMeta idMeta) {
        long res = 0;
        res |= id.getMachine();
        res |= id.getSeq() << idMeta.getMachineBits();
        res |= id.getTime() << idMeta.getTimeBitsStartPos();
        res |= id.getGenMethod() << idMeta.getGenMethodBitsStartPos();
        res |= id.getType() << idMeta.getTypeBitsStartPos();
        res |= id.getVersion() << idMeta.getVersionBitsStartPos();
        return res;
    }

	/**
	 * 根据long类型id,根据id元数据返回这个id的意义
	 * @param id
	 * @param idMeta
	 * @return
	 */
    protected Id doConvert(long id, IdMeta idMeta) {
        Id res = new Id();
        res.setMachine(id & idMeta.getMachineBitsMask());
        res.setSeq((id >>> idMeta.getMachineBits()) & idMeta.getSeqBitsMask());
        res.setTime((id >>> idMeta.getTimeBitsStartPos()) & idMeta.getTimeBitsMask());
        res.setGenMethod((id >>> idMeta.getGenMethodBitsStartPos()) & idMeta.getGenMethodBitsMask());
        res.setType((id >>> idMeta.getTypeBitsStartPos()) & idMeta.getTypeBitsMask());
        res.setVersion((id >>> idMeta.getVersionBitsStartPos()) & idMeta.getVersionBitsMask());
        return res;
    }
}