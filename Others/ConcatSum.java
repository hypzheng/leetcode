package Others;

import java.util.HashMap;
import java.util.Map;

public class ConcatSum
{
	public static void main(String[] args) {
		int[] array1 = {3, 5, 7, 9, 11, 13};
		System.out.println(concatenationsSum(array1));
	}

	public static long concatenationsSum(int[] a) {
		long[] ans = new long[1];
		long[] total = new long[1];
		Map<Integer, Integer> powMap = new HashMap<>();

		for (int i = 0; i < a.length; ++i) {
			int currentPow = (int)Math.log10(a[i]);
			powMap.put(currentPow, powMap.getOrDefault(currentPow, 0)+1);
			total[0] += a[i];
		}
		powMap.put(-1, a.length);
		powMap.forEach((key, val)-> ans[0] +=(long)Math.pow(10, key+1)*total[0]*val);
		return ans[0];
	}
}






