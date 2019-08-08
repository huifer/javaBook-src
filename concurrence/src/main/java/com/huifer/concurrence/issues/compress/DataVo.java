package com.huifer.concurrence.issues.compress;

import java.math.BigDecimal;


public class DataVo {

	/**
	 * 温度比值
	 */
	public static final BigDecimal wdPro = new BigDecimal("0.2");
	/**
	 * 湿度比值
	 */
	public static final BigDecimal sdPro = new BigDecimal("0.2");
	/**
	 * 气压比值
	 */
	public static final BigDecimal ylPro = new BigDecimal("0.2");


	public static void main(String[] args) {
		BigDecimal wdOld = new BigDecimal(1);
		BigDecimal sdOld = new BigDecimal(1);
		BigDecimal ylOld = new BigDecimal(1);
		BigDecimal wdNew = new BigDecimal(0.6);
		BigDecimal sdNew = new BigDecimal(0.6);
		BigDecimal ylNew = new BigDecimal(0.6);
		boolean ok = isOk(wdOld, sdOld, ylOld, wdNew, sdNew, ylNew);
		System.out.println(ok);
	}

	/**
	 * 简单比较 |(上一个温度-当前温度)|/上一个温度 <=0.5
	 */
	public static boolean isOk(BigDecimal wdOld, BigDecimal sdOld, BigDecimal ylOld, BigDecimal wdNew, BigDecimal sdNew, BigDecimal ylNew) {

		BigDecimal wdDiv = wdOld.subtract(wdNew).abs().divide(wdOld, 2, BigDecimal.ROUND_DOWN);
		BigDecimal sdDiv = sdOld.subtract(sdNew).abs().divide(sdOld, 2, BigDecimal.ROUND_DOWN);
		BigDecimal ylDiv = ylOld.subtract(ylNew).abs().divide(ylOld, 2, BigDecimal.ROUND_DOWN);

		if (wdDiv.compareTo(wdPro) <= 0 && sdDiv.compareTo(sdPro) <= 0 && ylDiv.compareTo(ylPro) <= 0) {
			return true;
		} else {
			return false;
		}
	}

}
