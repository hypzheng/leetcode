import java.util.*;

/**
 * Question: given a value, find indexes from an array that the sum of those elements equals to the given value.
 * Assume each input has only 1 solution, and an element can only be used once.
 */
public class TwoSum
{
	public static void main(String[] args) {
		int[] sample1 = {1, 2, 3};
		int[] sample2 = {4, 6, 8, 10};
		
		System.out.println(Arrays.toString(twoSum(sample1, 5)));
		System.out.println(Arrays.toString(twoSum(sample2, 11)));
	}
	
	public static int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[] {-1, -1};
	}
}
	
