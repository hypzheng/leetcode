/**
 * Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward.
 * For example, 121 is palindrome while 123 is not.
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 */
public class E9_Palindrome
{
	public static void main(String[] args) {
		System.out.println("Are the given numbers palindrome?");
		int[] intArray = {121, -121, 11, 10, 2, -1, 100301};
		for (int i: intArray) {
			System.out.println(i + " " + isPalindrome2(i));
		}
	}

	/**
	 * 转换成string: runtime 7ms, beat 64/31.
	 */
	public static boolean isPalindrome1(int x) {
		String str = Integer.toString(x);
		if (str.charAt(0) == '-') {
			return false;	//若x为负数，则一定不是回文数
		}
		int strLen = str.length();
		for (int i = 0; i < str.length()/2; i++) {
			if (str.charAt(i) == str.charAt(strLen - 1)) {
				strLen--;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 不转换成string: runtime 6ms, beat 100/54.
	 */
	public static boolean isPalindrome2(int x) {
		int val = x;
		int ans = 0;
		if (x < 0) {
			return false;
		}
		while (val != 0) {
			ans = ans * 10 + val % 10;	//令ans为x反转的数值
			val /= 10;
		}
		return x == ans;
	}
}
