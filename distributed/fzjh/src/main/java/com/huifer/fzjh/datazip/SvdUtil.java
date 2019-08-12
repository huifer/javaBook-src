package com.huifer.fzjh.datazip;

import Jama.Matrix;

public class SvdUtil {

	private SvdUtil() {
		throw new RuntimeException("this is a util class");
	}


	public static double[][] svd(double[][] inputMatrix, int rank) {
		Matrix matrix = new Matrix(inputMatrix);
		int maxRank = matrix.rank();
		Matrix s = matrix.svd().getS();
		Matrix u = matrix.svd().getU();
		Matrix v = matrix.svd().getV();

		if (rank > maxRank) {
			rank = maxRank;
		}

		s = new Matrix(MyMatrix.rankedMatrixS(new MyMatrix(s.getArrayCopy()), rank));
		u = new Matrix(MyMatrix.rankedMatrixUV(new MyMatrix(u.getArrayCopy()), rank));
		v = new Matrix(MyMatrix.rankedMatrixUV(new MyMatrix(v.getArrayCopy()), rank));

		MyMatrix composed = new MyMatrix(u.times(s).times(v.transpose()).getArray());
		return composed.getMatrix();
	}


	private static class MyMatrix {
		private double[][] matrix;

		public MyMatrix(double[][] matrix) {
			this.matrix = matrix;
		}

		public int getRowCount() {
			return this.matrix.length;
		}

		public static double[][] rankedMatrixS(MyMatrix myMatrix, int rank) {
			double[][] rankedMatrix = new double[rank][rank];
			for (int i = 0; i < rank; i++) {
				double[] rowMatrix = myMatrix.getMatrix()[0];
				System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
			}
			return rankedMatrix;
		}

		public static double[][] rankedMatrixUV(MyMatrix myMatrix, int rank) {
			int rows = myMatrix.getRowCount();
			double[][] rankedMatrix = new double[rows][rank];
			for (int i = 0; i < rows; i++) {
				double[] rowMatrix = myMatrix.getMatrix()[i];
				System.arraycopy(rowMatrix, 0, rankedMatrix[i], 0, rank);
			}
			return rankedMatrix;
		}

		public double[][] getMatrix() {
			return matrix;
		}
	}
}
