package Others;

import java.util.*;

public class CommonPrefix
{
	public static void main(String[] args) {
		String[] strings1 = new String[] {"abcd", "abcde", "abbcde", "abd"};
		String[] strings2 = new String[] {"damn", "what"};
		String[] strings3 = new String[] {"you"};

		System.out.println(longestCommonPrefix(strings1));
		System.out.println(longestCommonPrefix(strings2));
		System.out.println(longestCommonPrefix(strings3));
	}

	public static String longestCommonPrefix(String[] arr) {
		int sizeArr = arr.length;
		if (sizeArr == 0) {
			return "GIVEN IS EMPTY";
		} else if (sizeArr == 1) {
			return arr[0];
		} else {
			Arrays.sort(arr);
		}

		int count = 0;
		String prefix;
		int shortestLength = Math.min(arr[0].length(), arr[sizeArr-1].length());
		for (int i = 0; i < shortestLength; i++) {
			if (arr[0].charAt(i) == arr[sizeArr-1].charAt(i)) {
				count++;
			}
		}
		prefix = arr[0].substring(0, count);
		if (count == 0) {
			return "NOT FOUND";
		}
		return prefix;
	}
}