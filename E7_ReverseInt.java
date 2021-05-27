/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed
 * 32-bit integer range [-2^31, 2^31-1], then return 0.
 *
 * 给你一个32位的有符号整数x, 返回将x中的数字部分反转后的结果.
 * 如果反转后整数超过32位的有符号整数的范围[-2^31, 2^31-1], 就返回 0.
 * 假设环境不允许存储64位整数（有符号或无符号）.
 */
public class E7_ReverseInt
{
	/**
	 * 注意1<<31和-(1<<31)都会得到Integer.MIN_VALUE = -2^31.
	 * 但是(int)Math.pow(2,31)会得到Integer.MAX_VALUE = 2^31-1.
	 */
	public static void main(String[] args)
	{
		int[] intArray = {123, -456, 780, 909, 666, 0, 123456789};
		for (int i: intArray) {
			System.out.println(reverse1(i));
		}
	}

	/**
	 * 1.Pop&Push: 时空复杂度 O(log|x|)/O(1), beat 100/53.
	 * 	 时间复杂度中, 翻转的次数即x十进制的位数
	 */
	public static int reverse1(int x)
	{
		int ans = 0;
		int val = x;
		int rem;

		while (val != 0) {
			rem = val % 10;	//x除以10的余数：最右一位数字
			val /= 10;	//x除以10的商：除去最右一位的剩余数字
			if (ans > Integer.MAX_VALUE/10 || ans < Integer.MIN_VALUE/10) {
				return 0;
			}
			ans = ans * 10 + rem;	//让ans成为反转后的最左一位数字
		}
		return ans;
	}

	/**
	 * 2.使用long进行Pop&Push: 时空复杂度 O(log|x|)/O(1), beat 100/71.
	 */
	public static int reverse2(int x)
	{
		long ans = 0;
		while (x != 0) {
			ans = ans * 10 + x % 10;
			x /= 10;
		}
		//条件运算符 a = (b) ? 1 : 2 若条件b为真则令a=1, 若条件b为假则令a=2.
		return (int)ans == ans ? (int)ans : 0;
	}

	/**
	 * 3.转换成字符串: beat 27/57
	 */
	public static int reverse3(int x)
	{
		String xStr = Integer.toString(x);
		String ans = xStr;
		int flag = 1;	//用于给数值标注正负
		if (x < 0) {
			flag = -1;
			ans = xStr.substring(1);	//若x为负数，则删除负号
		}
		try {
			return Integer.parseInt(new StringBuilder(ans).reverse().toString()) * flag;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
