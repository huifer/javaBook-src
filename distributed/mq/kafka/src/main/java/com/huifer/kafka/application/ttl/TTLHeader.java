package com.huifer.kafka.application.ttl;

import org.apache.kafka.common.header.Header;

/**
 * <p>Title : TTLHeader </p>
 * <p>Description :  </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class TTLHeader implements Header {


    /**
     * 超时时间 : 秒
     */
    private long ttl;

    public TTLHeader(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String key() {
        return "ttl";
    }

    @Override
    public byte[] value() {
        long rs = this.ttl;
        return BytesUtils.long2byte(rs);
    }
}
