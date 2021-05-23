package Others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class GroupMeanSum
{
	public static void main(String[] args) {
		int[][] arr1 = {{3, 3, 4, 2},
						{4, 4},
						{4, 0, 3, 3, 6, 8},
						{2, 3},
						{3, 3, 3}};
		System.out.println(Arrays.deepToString(arr1));
		groupByMean(arr1);

	}

	public static void groupByMean(int[][] arr) {
		int[][] result = new int[9][9];
		double[] sum = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sum[i] += arr[i][j];
			}
			sum[i] = sum[i] / arr[i].length;
		}

		for (int i = 0; i < sum.length; i++) {
			double flag = sum[i];
			int k = 0;
			for (int j = i+1; j < sum.length; j++) {
				if (flag != sum[j]) {
					result[i][k] = i;
				}
			}
		}

		System.out.println(Arrays.toString(sum));
		List list = new ArrayList<>();



		//return result;
	}
}
