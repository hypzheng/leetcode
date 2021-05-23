/**
 * Given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * Assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class M2_Add2Nums
{
	public static void main(String[] args) {
//		ListNode list1 = new ListNode(3);
//		list1.next = new ListNode(6, list1.next);
//		list1.next.next = new ListNode(5);
//
//		ListNode list2 = new ListNode(4, list1);
//		ListNode list3 = new ListNode(2, list2);
//
////		Solution s = new Solution();
////		Easy.ListNode list3 = s.add2Nums(list1, list2);
//
//		System.out.println(list1.next.next.val);
	}

	public ListNode add2Nums(ListNode l1, ListNode l2) {

		ListNode list = new ListNode();
		int sum = l1.val + l2.val;

		if (sum < 10) {
			list.val = sum;
		} else {
			list.val = sum - 10;
			list.next = new ListNode(1);

		}
		return list;
	}
}
