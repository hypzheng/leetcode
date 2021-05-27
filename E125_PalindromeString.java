/**
 * Given a string s, determine if it is a palindrome,
 * considering only alphanumeric characters and ignoring cases.
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 */
public class E125_PalindromeString
{
	public static void main(String[] args)
	{
		String str1 = "A man, a plan, a canal: Panama";
		String str2 = "race a car";

		System.out.println(isPalindrome1(str1));
		System.out.println(isPalindrome2(str2));
	}

	/**
	 * 1.遍历筛选: 时空复杂度 O(|s|)/O(|s|), beat 44/88
	 *	 StringBuffer线程安全, StringBuilder速度快
	 */
	public static boolean isPalindrome1(String s)
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			//c的区间为['0', '9']或['a', 'z']或['A', 'Z']
			if (Character.isLetterOrDigit(c)) {
				str.append(Character.toLowerCase(c));
			}
		}
		StringBuilder strRev = new StringBuilder(str).reverse();
		return str.toString().equals(strRev.toString());
	}

	/**
	 * 2.双指针: 时空复杂度 O(|s|)/O(1), beat 98/98
	 *	 直接在原字符串s上使用双指针
	 */
	public static boolean isPalindrome2(String s)
	{
		int left = 0, right = s.length() - 1;
		while (left < right) {
			if (!Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			} else if (!Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			} else {
				if (Character.toLowerCase(s.charAt(left)) !=
						Character.toLowerCase(s.charAt(right))) {
					return false;
				}
				left++;
				right--;
			}
		}
		return true;
	}
}
