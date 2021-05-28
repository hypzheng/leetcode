import java.util.*;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
public class E1_TwoSum
{
	public static void main(String[] args)
	{
		int[] sums = {5, 10, 20, 30};
		int[][] intArray = {
				{1, 2, 3}, {7, 6, 5, 4}, {12, 11, 10, 9, 8}, {13, 14, 15}
		};
		for (int i = 0; i < sums.length; i++) {
			System.out.println(i+1 + ".  数组" + Arrays.toString(intArray[i]));
			System.out.println("\t和为" + sums[i] + ",\t对应项数为" +
					Arrays.toString(twoSum2(intArray[i], sums[i])));
		}
	}

	/**
	 * 1.暴力破解: 时空复杂度 O(n2)/O(1), beat 100/63
	 */
	private static int[] twoSum1(int[] nums, int target)
	{
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
	 * 2.哈希表: 时空复杂度 O(n)/O(n), beat 44/85
	 */
	public static int[] twoSum2(int[] nums, int target)
	{
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int diff = target - nums[i];
			//使用哈希表来存储target与nums[x]的差
			//若哈希表中存在某个diff, 说明该diff也在nums数组中
			if (map.containsKey(diff) && map.get(diff) != i) {
				return new int[] {map.get(diff), i};
			}
			//若某个diff不在哈希表中, 则将其对应的nums[x]存入哈希表
			map.put(nums[i], i);
		}
		return new int[] {-1, -1};
	}
}
	
