import java.util.*;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * You can return the answer in any order.
 */
public class E1_TwoSum
{
	public static void main(String[] args) {
		int[][] intArray = {
				{1, 2, 3}, {7, 6, 5, 4}, {12, 11, 10, 9, 8}, {13, 14, 15}
		};
		System.out.println(Arrays.toString(twoSum1(intArray[0], 5)));
		System.out.println(Arrays.toString(twoSum1(intArray[1], 10)));
		System.out.println(Arrays.toString(twoSum2(intArray[2], 20)));
		System.out.println(Arrays.toString(twoSum2(intArray[3], 30)));
	}

	/**
	 * 1. Brute Force: beat 100/63
	 * time: O(n2), space: O(1)
	 */
	private static int[] twoSum1(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[] {-1, -1};
	}

	/**
	 * 2. Two-pass Hash Table: beat 32/90
	 * time: O(n), space: O(n)
	 */
	private static int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int diff = target - nums[i];
			// if map contains diff, and diff is not num[i] itself
			if (map.containsKey(diff) && map.get(diff) != i) {
				return new int[] {i, map.get(diff)};
			}
		}
		return new int[] {-1, -1};
	}
}
	
