package Medium;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * i.e. for input "abcabcbb", proper substring is "abc", output = 3.
 */
public class LC3_LongestSubstringNRC
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
