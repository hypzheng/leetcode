import java.util.*;

public class ReshapeMatrix
{
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1, 2}, {3, 4}, {5, 6}};
		int[][] result = matrixReshape(matrix, 2, 3);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}

	public static int[][] matrixReshape(int[][] mat, int m, int n) {
		int c = mat[0].length;
		int[][] ans = new int[m][n];
		int k = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				ans[i][j] = mat[k / c][k % c];
				k++;
			}
		}
		return ans;
	}
}