import java.util.Arrays;

/**
 * Given an array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Return the indices of the two numbers (1-indexed) as an integer array answer of size 2,
 * where 1 <= answer[0] < answer[1] <= numbers.length.
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice.
 *
 * 给定一个已按照升序排列的整数数组 numbers，请你从数组中找出两个数满足相加之和等于目标数 target。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数，
 * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= number.length。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 */
public class E167_TwoSum2
{
	public static void main(String[] args)
	{
		int[] sums = {0, 9, 9, 10, 12};
		int[][] intArray = {
				{-1, 0, 1}, {3, 6, 9}, {3, 4, 5}, {2, 4, 6, 8}, {3, 3, 6, 6, 12, 12, 18, 18}
		};
		for (int i = 0; i < sums.length; i++) {
			System.out.println(i+1 + ".  数组" + Arrays.toString(intArray[i]));
			System.out.println("\t和为" + sums[i] + ",\t对应项数+1为" +
					Arrays.toString(twoSum2(intArray[i], sums[i])));
		}
		System.out.println(10>>>1);
	}

	/**
	 * 1.二分查找: 时空复杂度 O(n log n)/O(1), beat 25/77
	 * 	 数组的长度是 n, 需要遍历数组一次确定第一个数, 时间复杂度是 O(n)
	 * 	 寻找第二个数使用二分查找, 时间复杂度是 O(log n)
	 */
	public static int[] twoSum1(int[] numbers, int target)
	{
		for (int i = 0; i < numbers.length; i++) {
			//把 target-numbers[i] 看作原始二分查找中的key
			int key = target - numbers[i];
			//从 numbers[i] 右侧开始, 避免重复查找
			int mid, low = i + 1, high = numbers.length - 1;

			while (low <= high) {
				mid = (high - low)/2 + low;
				if (numbers[mid] < key) {
					low = mid + 1;
				} else if (numbers[mid] > key) {
					high = mid - 1;
				} else {
					return new int[] {i + 1, mid + 1};
				}
			}
		}
		return new int[] {-1, -1};
	}

	/**
	 * 2.双指针: 时空复杂度 O(n)/O(1), beat 100/87
	 * 	 两个指针分别指向第一个元素位置和最后一个元素的位置.
	 *   每次计算两个指针指向的两个元素之和，并和目标值比较.
	 */
	public static int[] twoSum2(int[] numbers, int target)
	{
		int left = 0, right = numbers.length - 1;
		while (left < right) {
			if (numbers[left] + numbers[right] < target) {
				left++;
			} else if (numbers[left] + numbers[right] > target) {
				right--;
			} else {
				return new int[] {left + 1, right + 1};
			}
		}
		return new int[] {-1, -1};
	}

	/**
	 * 3.双指针二分查找: 时空复杂度最优 O(log n)/O(1), 最差 O(n)/O(1), beat 100/16
	 */
	public static int[] twoSum3(int[] numbers, int target)
	{
		int mid, left = 0, right = numbers.length - 1;
		while (left < right) {
			//二分查找缩小搜索范围
			mid = (right - left + 1)/2 + left; //或者 mid = (left + right) >>> 1
			if (numbers[mid] < target - numbers[right]) {
				left = mid + 1;
			} else if (numbers[mid] > target - numbers[left]) {
				right = mid - 1;
			}
			//双指针进行比较
			if (numbers[left] + numbers[right] < target) {
				left++;
			} else if (numbers[left] + numbers[right] > target) {
				right--;
			} else {
				return new int[] {left + 1, right + 1};
			}
		}
		return new int[] {-1, -1};
	}
}
