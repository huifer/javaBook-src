package com.huifer.idgen.my.service.populator;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;
import com.huifer.idgen.my.service.bean.enums.IdType;
import com.huifer.idgen.my.service.util.TimeUtils;

/**
 * @author: wang
 * @description:
 */
public abstract class BasePopulator implements IdPopulator, ResetPopulator {

    protected long seq = 0;
    protected long lastTime = -1L;

    /**
     * 雪花算法部分
     */
    @Override
    public void populatorId(Id id, IdMeta idMeta) {
        long timeStamp = TimeUtils.genTime(IdType.parse(id.getType()));
        TimeUtils.validateTime(lastTime, timeStamp);
        if (timeStamp == lastTime) {
            seq++;
            seq &= idMeta.getSeqBitsMask();
            if (seq == 0) {
                timeStamp = TimeUtils.tillNextTime(lastTime, IdType.parse(id.getType()));
            }
        } else {
            lastTime = timeStamp;
            seq = 0;
        }
        id.setSeq(seq);
        id.setTime(timeStamp);
    }

    @Override
    public void reset() {
        this.seq = 0;
        this.lastTime = -1L;
    }
}