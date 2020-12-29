import java.util.Arrays;

public class MutateArray
{
	public static void main(String[] args) {
		int[] arr1 = new int[] {8, 1, 3, 7, 6, 9};
		System.out.println(Arrays.toString(mutateTheArray(arr1.length, arr1)));
	}

	public static int[] mutateTheArray(int n, int[] a) {
		int[] b = new int[n];
		if (n == 1) {
			b = a;
		} else {
			for (int i = 0; i < n; i++) {
				if (i == 0) {
					b[i] = a[i] + a[i+1];
				} else if (i == n-1) {
					b[i] = a[i] + a[i-1];
				} else {
					b[i] = a[i-1] + a[i] + a[i+1];
				}
			}
		}
		return b;
	}
}
