package com.huifer.idgen.my.service.populator;

import com.huifer.idgen.my.service.bean.Id;
import com.huifer.idgen.my.service.bean.IdMeta;
import com.huifer.idgen.my.service.bean.enums.IdType;
import com.huifer.idgen.my.service.util.TimeUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: wang
 * @description:
 */
public class AtomicIdPopulator implements IdPopulator, ResetPopulator {

    private AtomicReference<Param> paramAtomicReference = new AtomicReference<>(new Param());

    @Override
    public void populatorId(Id id, IdMeta idMeta) {
        Param oldParam;
        Param newParam;
        long timeStamp;
        long seq;
        while (true) {
            oldParam = paramAtomicReference.get();
            timeStamp = TimeUtils.genTime(IdType.parse(id.getType()));
            TimeUtils.validateTime(oldParam.lastTime, timeStamp);
            seq = oldParam.seq;
            if (timeStamp == oldParam.lastTime) {
                seq++;
                seq &= idMeta.getSeqBitsMask();
                if (seq == 0) {
                    timeStamp = TimeUtils.tillNextTime(oldParam.lastTime, IdType.parse(id.getType()));
                }
            } else {
                seq = 0;
            }

            newParam = new Param();
            newParam.seq = seq;
            newParam.lastTime = timeStamp;
            if (paramAtomicReference.compareAndSet(oldParam, newParam)) {
                id.setTime(timeStamp);
                id.setSeq(seq);
                break;

            }
        }

    }

    @Override
    public void reset() {
        paramAtomicReference = new AtomicReference<>(new Param());
    }

    static class Param {

        private long seq = 0;
        private long lastTime = -1L;
    }
}