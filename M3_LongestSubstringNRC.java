import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s, 找出其中不含有重复字符的最长子串的长度.
 * 例如: 输入"abcabcbb", 无重复字符的最长子串是"abc", 所以其长度为3.
 */
public class M3_LongestSubstringNRC
{
	private static final int SIZE = 128;

	public static void main(String[] args) {
		String[] strArray = {"abcabcbb", "dddddd", "pwwkew", "xyxyz", ""};
		for (String s : strArray) {
			System.out.print(longestSubstringLength1(s) + " ");
		}
		System.out.println();
		for (String s : strArray) {
			System.out.print(longestSubstringLength2(s) + " ");
		}
		System.out.println();
		for (String s : strArray) {
			System.out.print(longestSubstringLength3(s) + " ");
		}
		System.out.println();
	}

	/**
	 * 滑动窗口: beat 90/55.
	 * 时间复杂度: O(n).
	 */
	private static int longestSubstringLength(String s)
	{
		Map<Character, Integer> map = new HashMap<>();
		int maxLen = 0;	//记录不重复的最长子串的长度
		int left = 0;	//滑动窗口左指针

		for (int i = 0; i < s.length() ; i++)
		{
			//判断当前字符是否在map中
			if (map.containsKey(s.charAt(i)))
			{
				/*	情况1: 当前字符包含在当前有效的子串中. 例如: abca.
					当遍历到c时, map中已添加了a, b, c. 此时left = 0, 当前有效最长子串是abc.
					继续遍历到第二个a时, 由于map中已存在a, 所以需要更新left为map.get(a) + 1. 此时left = 1, 当前有效最长子串是bca.

					情况2: 当前字符不包含在当前有效的子串中. 例如: abba.
					当遍历到第一个b时, map中已添加了a和b. 此时left = 0, 当前有效最长子串是ab.
					继续遍历到第二个b时, 由于map中已存在b, 所以需要更新left为map.get(b) + 1. 此时left = 2, 当前有效最长子串是ba.
				 */
				left = Math.max(left, map.get(s.charAt(i)) + 1);
			}

			//若当前字符不在map中, 将该字符添加到map
			map.put(s.charAt(i) , i);	//map.put(当前字符, 该字符在数组中的下标)
			maxLen = Math.max(maxLen, i - left + 1);
		}
		return maxLen;
	}


	/**
	 * 1. Sliding window: beat 100/41. time: O(2n), space: O(min(m, n)).
	 */
	private static int longestSubstringLength1(String s) {
		int[] window = new int[SIZE];
		int left = 0;	// left pointer
		int right = 0;	// right pointer
		int result = 0;	// record the max length

		while (right < s.length()) {
			char r = s.charAt(right);
			window[r]++;
			while (window[r] > 1) {
				char l = s.charAt(left);
				window[l]--;
				left++;
			}
			result = Math.max(result, right - left + 1);
			right++;
		}
		return result;
	}

	/**
	 * 2. Sliding window: beat 100/27
	 */
	private static int longestSubstringLength2(String s) {
		int[] window = new int[SIZE];
		int start = 0; // start position of window
		int result = 0;

		// assume no current char has appeared yet
		for (int i = 0; i < SIZE; i++) {
			window[i] = -1;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			start = Math.max(start, window[c] + 1);
			result = Math.max(result, i - start + 1);
			window[c] = i ;
		}
		return result;
	}

	/**
	 * 3. Double pointers: beat 95/81
	 */
	private static int longestSubstringLength3(String s) {
		int ans = 0, temp;	// final answer, temp length
		int left = 0, right;	// left pointer, right pointer

		for (right = left; right < s.length(); right++) {
			for (int i = left; i < right; i++) {
				if (s.charAt(i) == s.charAt(right)) {
					temp = right - left;
					ans = Math.max(ans, temp);
					left = i + 1;
					break;
				}
			}
		}
		ans = Math.max(ans, right-left);
		return ans;
	}
}
