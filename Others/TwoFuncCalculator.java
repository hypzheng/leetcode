package Others;

/**
 * Has only 2 operations: multiply by 2 & substract 1
 * Calculate the minimum steps from input A to output B
 * Test cases: n = 1 to 9999
 */
public class TwoFuncCalculator {
    public static void main(String[] args) {
        System.out.println(brokenCalc(3, 18));
        System.out.println(brokenCalc(9, 33));
    }

    public static int brokenCalc(int x, int y) {
        int answer = 0;
        while (x < y) {
            if (y % 2 == 0) {
                y = y / 2;
            } else {
                y++;
            }
            answer++;
        }
        int leftover = x - y; // because x >= y now
        return answer + leftover;
    }
}