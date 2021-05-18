package Easy;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed
 * 32-bit integer range [-2^31, 2^31-1], then return 0.
 */
public class LC7_ReverseInt
{
	/**
	 * Both 1<<31 and -(1<<31) gives Integer.MIN_VALUE = -2^31.
	 * However (int)Math.pow(2,31) gives Integer.MAX_VALUE = 2^31-1.
	 */
	public static void main(String[] args) {
		int[] intArray = {123, -456, 780, 909, 666, 0, 123456789};
		for (int i: intArray) {
			System.out.println(reverse2(i));
		}
	}

	/**
	 * 1. Pop&Push: runtime 1ms, beat 100/71.
	 */
	public static int reverse1(int x) {
		int ans = 0;
		while (x != 0) {
			int pop = x % 10; //最右一位数字
			x /= 10; //除去最右一位的剩余数字

			if (ans > Integer.MAX_VALUE/10 || ans < Integer.MIN_VALUE/10) {
				return 0;
			}
			ans = ans * 10 + pop; //让pop成为反转后的最左一位数字
		}
		return ans;
	}

	/**
	 * 2. StringBuilder: runtime 2ms, beat 27/57
	 */
	public static int reverse2(int x) {
		//条件运算符 a = (b) ? 1 : 2 若条件b为真则令a=1, 若条件b为假则令a=2.
		String xStr = Integer.toString(x);
		String ans = xStr;
		int flag = 1;	//用于给数值标注正负
		if (x < 0) {
			flag = -1;
			ans = xStr.substring(1);
		}
		try {
			return Integer.parseInt(new StringBuilder(ans).reverse().toString()) * flag;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
