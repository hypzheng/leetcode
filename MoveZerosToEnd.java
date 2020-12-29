import java.util.*;

public class MoveZerosToEnd
{
	public static void main(String[] args) {
		int[] arr1 = new int[] {0, 1, 3, 0, 6, 9};
		moveZeros(arr1);
		System.out.println(Arrays.toString(arr1));
	}

	public static void moveZeros(int[] nums) {
		int k = 0;
		for (int n: nums) {
			if (n != 0) {
				nums[k++] = n;
			}
		}
		while (k < nums.length) {
			nums[k++] = 0;
		}
	}
}