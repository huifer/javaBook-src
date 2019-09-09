package com.huifer.bigfile.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BandwidthLimiter {
    protected static final Logger log = LoggerFactory.getLogger(BandwidthLimiter.class);

    /**
     * 1024
     */
    private static Long kb = 1024L;


    /**
     * 最小计数块长度(单位:字节)
     */
    private static Long chunkLength = 1024L;


    /**
     * 将发送或接收多少字节
     */
    private int bytesWillBeSentOrReceive = 0;


    /**
     * 当最后一块被发送或接收的时间
     */
    private long lastPieceSentOrReceiveTick = System.nanoTime();


    /**
     * 默认的速度是 1024KB/s
     */
    private int maxRate = 256; //2M带宽是256KB/s


    /**
     * 时间成本来发送 CHUNK_LENGTH 字节纳秒
     */
    private long timeCostPerChunk = (1000000000L * chunkLength) / (this.maxRate * kb);

    public BandwidthLimiter(int maxRate) {
        this.setMaxRate(maxRate);
    }

    /**
     * 设置一个最大的下载速度，如果速度是0，这意味着没有带宽限制
     *
     * @param maxRate 最大下载速度
     * @throws IllegalArgumentException
     */

    public synchronized void setMaxRate(int maxRate) throws IllegalArgumentException {

        if (maxRate < 0) {
            throw new IllegalArgumentException("maxRate can not less than 0");
        }

        this.maxRate = maxRate < 0 ? 0 : maxRate;
        if (maxRate == 0) {
            this.timeCostPerChunk = 0;
        } else {
            this.timeCostPerChunk = (1000000000l * chunkLength) / (this.maxRate * kb);
        }
    }


    /**
     * 下一个字节应该是带宽限制
     */
    public synchronized void limitNextBytes() {
        this.limitNextBytes(1);
    }


    /**
     * 下一个len字节应该做带宽限制
     *
     * @param len
     */

    public synchronized void limitNextBytes(int len) {
        this.bytesWillBeSentOrReceive += len;
        /* We have sent CHUNK_LENGTH bytes */
        while (this.bytesWillBeSentOrReceive > chunkLength) {
            long nowTick = System.nanoTime();
            long missedTime = this.timeCostPerChunk - (nowTick - this.lastPieceSentOrReceiveTick);
            if (missedTime > 0) {
                try {
                    Thread.sleep(missedTime / 1000000, (int) (missedTime % 1000000));
                } catch (InterruptedException e) {
                    log.error("网络异常,{}", e);
                }
            }
            this.bytesWillBeSentOrReceive -= chunkLength;
            this.lastPieceSentOrReceiveTick = nowTick + (missedTime > 0 ? missedTime : 0);
        }
    }
}
