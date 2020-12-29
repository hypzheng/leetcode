import java.util.HashMap;
import java.util.Map;

/**
 * Question: find the first element that appears once only in an array.
 */
public class NoRepeat
{
	public static void main(String[] args) {
		int[] array1 = new int[] {1, 3, 5, 1, 3, 7};
		int[] array2 = new int[] {2, 4, 6, 8, 6, 4};
		System.out.println(nonRepeating1(array1));
		System.out.println(nonRepeating2(array2));
	}

	public static int nonRepeating1(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int j;
			for (j = 0; j < arr.length; j++)
				if (i != j && arr[i] == arr[j])
					break;
			if (j == arr.length)
				return arr[i];
		}
		return -1;
	}

	public static int nonRepeating2(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (map.get(arr[i]) == 1) {
				return arr[i];
			}
		}
		return -1;
	}
}
