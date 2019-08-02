package com.huifer.idgen.my.service.util;

import com.huifer.idgen.my.service.IdGenException;
import com.huifer.idgen.my.service.bean.enums.IdType;

/**
 * @author: wang
 * @description:
 */
public class TimeUtils {

	public static final long EPOCH = 1420041600000L;

	public static void validateTime(long lastTime, long timestamp) {
		if (timestamp < lastTime) {
			throw new IdGenException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d second/milisecond.",
					lastTime - timestamp));
		}
	}

	public static long tillNextTime(long lastTime, IdType idType) {
		long genTime = genTime(idType);
		while (genTime <= lastTime) {
			genTime = genTime(idType);
		}
		return genTime;
	}

	public static long genTime(IdType idType) {
		if (idType == IdType.TYPE_ONE) {
			return (System.currentTimeMillis() - EPOCH) / 1000L;
		} else if (idType == IdType.TYPE_TWO) {
			return (System.currentTimeMillis() - EPOCH);
		}
		return (System.currentTimeMillis() - EPOCH) / 1000L;
	}

	private TimeUtils() {
	}
}