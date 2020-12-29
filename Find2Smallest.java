/**
 * Question: find the 1st and 2nd smallest elements in an array.
 */
public class Find2Smallest
{
	public static void main(String[] args) {
		int[] array1 = new int[] {12, 9, 3, 24, 6, 18};
		int[] array2 = new int[] {12, 12};
		twoSmallest(array1);
		twoSmallest(array2);
	}

	public static void twoSmallest(int[] array) {
		if (array.length < 2) {
			System.out.println("Invalid Input");
		}

		int firstSmall = array[0];
		int secondSmall = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] < firstSmall) {
				secondSmall = firstSmall;
				firstSmall = array[i];
			} else if (array[i] < secondSmall && firstSmall != secondSmall) {
				secondSmall = array[i];
			}
		}
		System.out.println("First smallest is " + firstSmall);
		System.out.println("Second smallest is " + secondSmall);
	}
}
