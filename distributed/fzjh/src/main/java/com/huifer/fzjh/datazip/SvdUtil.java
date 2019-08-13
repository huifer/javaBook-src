package com.huifer.fzjh.datazip;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SvdUtil {

	private SvdUtil() {
		throw new RuntimeException("this is a util class");
	}

	public static void main(String[] args) {
		double[][] d = {
				{0, 0, 1},
				{0, 0, 1},
				{0, 0, 0},
		};
		Matrix matrix = new Matrix(d);
		SingularValueDecomposition svd1 = matrix.svd();
		// 奇异值确认: 删除左对角线趋近0的行列
		Matrix s = svd1.getS();
		double[][] sArr = s.getArrayCopy();
		for (int i = 0; i < sArr.length; i++) {
			double[] doubles = sArr[i];
			for (int j = 0; j < doubles.length; j++) {
				double ij = sArr[i][j];
				if (Math.abs(0 - ij) < 0.00000001) {
					System.out.println("第" + i + "行");
					System.out.println("第" + j + "列");
				}
			}
			System.out.println();
		}

//		print(svd1.getS().getArrayCopy());


	}

	private static void print(double[][] arrayCopy) {
		for (double[] doubles : arrayCopy) {
			for (double aDouble : doubles) {
				System.out.print(aDouble + "\t");
			}
			System.out.println();
		}
	}

}
