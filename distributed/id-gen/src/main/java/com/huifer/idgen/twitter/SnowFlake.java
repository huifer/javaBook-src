package com.huifer.idgen.twitter;

import com.huifer.idgen.my.service.IdGenException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang
 * @description:
 */
@Slf4j
public class SnowFlake {

	/**
	 * 开始时间戳
	 */
	public static final long START_STMP = 1546272000000L;
	/**
	 * 序列号位数
	 */
	public static final long SEQUENCE_BIT = 12L;
	/**
	 * 机器表示位数
	 */
	public static final long MACHINE_BIT = 5L;
	/**
	 * 数据中心位数
	 */
	public static final long DATACENTER_BIT = 5L;

	/**
	 * 序列号最大值
	 */
	public static final long MAX_SEQUENCE_NUM = -1L ^ (-1L << SEQUENCE_BIT);
	/**
	 * 机器最大值
	 */
	public static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
	/**
	 * 数据中心最大值
	 */
	public static final long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
	/**
	 * 机器左移
	 */
	public static final long MAXCHINE_LEFT = SEQUENCE_BIT;
	/**
	 * 数据中心左移
	 */
	public static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
	/**
	 * 时间戳左移
	 */
	public final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;


	/**
	 * 数据中心id
	 */
	private long dataCenterId;
	/**
	 * 机器id
	 */
	private long machineId;
	/**
	 * 序列号
	 */
	private long sequence = 0L;
	/**
	 * 最后一次时间戳
	 */
	private long lastStmp = -1L;

	public SnowFlake(long dataCenterId, long machineId) {
		if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
			throw new IllegalArgumentException();
		}

		if (machineId > MAX_MACHINE_NUM || machineId < 0) {
			throw new IllegalArgumentException();
		}
		this.dataCenterId = dataCenterId;
		this.machineId = machineId;
	}


	public synchronized long genId() {
		long curStmp = System.currentTimeMillis();
		if (curStmp < lastStmp) {
			throw new IdGenException("当前时间 < 上一次操作时间");
		}
		if (curStmp == lastStmp) {
			// 时间戳相同时序列号+1
			sequence = (sequence + 1) & MAX_SEQUENCE_NUM;
			if (sequence == 0L) {
				curStmp = getNextMill();
			}
		} else {
			sequence = 0L;
		}
		lastStmp = curStmp;

		return (curStmp - START_STMP)
				<< TIMESTMP_LEFT                    // 时间戳
				| dataCenterId << DATACENTER_LEFT   // 数据中心
				| machineId << MAXCHINE_LEFT        // 机器标识
				| sequence                          // 序列号
				;
	}


	private long getNextMill() {
		long mill = System.currentTimeMillis();
		while (mill <= lastStmp) {
			mill = System.currentTimeMillis();
		}
		return mill;
	}


	public static void main(String[] args) {
		long dataCenterId = 2L;
		long machineId = 3L;
		SnowFlake snowFlake = new SnowFlake(dataCenterId, machineId);
		for (int i = 0; i < 10; i++) {
			long genId = snowFlake.genId();
			log.info("{}", genId);
		}
	}
}