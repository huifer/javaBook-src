package com.huifer.idgen.my.service;

import com.huifer.idgen.my.service.bean.Id;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: wang
 * @description: id生成器
 */
@Service
public interface GenIdService {

    /**
     * 生成id
     */
    long genId();

    /**
     * 生成的id反解{@link Id}
     *
     * @param id 数字id
     * @return 完整id
     */
    Id expId(long id);

    /**
     * 伪造id
     *
     * @param time 时间戳
     * @param seq  步长
     */
    long makeId(long time, long seq);

    long makeId(long time, long seq, long machine);

    /**
     * 伪造id
     *
     * @param genMethod 生产方法
     * @param time      时间戳
     * @param seq       步长
     * @param machine   机器id
     * @return id
     */
    long makeId(long genMethod, long time, long seq, long machine);

    /**
     * 伪造id
     *
     * @param type      类型
     * @param genMethod 生产方法
     * @param time      时间戳
     * @param seq       步长
     * @param machine   机器id
     * @return id
     */
    long makeId(long type, long genMethod, long time, long seq, long machine);

    /**
     * 伪造id
     *
     * @param version   版本号
     * @param type      类型
     * @param genMethod 生产方法
     * @param time      时间戳
     * @param seq       步长
     * @param machine   机器id
     * @return id
     */
    long makeId(long version, long type, long genMethod, long time, long seq, long machine);

    /**
     * 时间戳格式化{@link Date}
     *
     * @param time 时间戳
     * @return date
     */
    Date transTime(long time);
}