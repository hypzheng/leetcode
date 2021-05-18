/**
 * Has only 2 operations: copy & paste
 * Given the initial character is A, where n = 1
 * Calculate the minimum steps to get n of A (AA...A)
 * Test cases: n = 1 to 9999
 */
public class TwoKeysKeyboard { 
    public static void main(String[] args) {
        System.out.println(minStep(15));
    }

    public static int minStep(int n) {
        int r = 0;
        int k = 2;
        while (k <= n) {
            if (n % k == 0) {
                r += k;
                n = n / k;
            } else {
                k++;
            }
        }
        return r;
    }
}