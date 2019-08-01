package com.huifer.idgen.my.service.util;

import java.util.Arrays;

/**
 * @author: wang
 * @description:
 */
public class CommonUtils {

	public static String[] switch_on = {"ON", "TRUE"};
	public static String[] switch_off = {"OFF", "FALSE"};

	public static boolean isOn(String switch_) {
		return Arrays.asList(switch_on).contains(switch_);
	}


	public static boolean isPropKeyOn(String key) {
		String property = System.getProperty(key);
		return Arrays.asList(switch_on).contains(property);
	}
}