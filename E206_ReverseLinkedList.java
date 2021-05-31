/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * 已知单链表的头节点，反转链表，并返回反转后的链表。
 */
public class E206_ReverseLinkedList
{
	public static void main(String[] args)
	{
		LinkedList list3 = new LinkedList();
		LinkedList list4 = new LinkedList();
		int[] digitArray3 = {8, 6, 4, 2, 0};
		int[] digitArray4 = {9, 7, 5, 3};

		for (int i : digitArray3) {
			list3.insertTail(i);
		}
		for (int i: digitArray4) {
			list4.insertHead(i);
		}

		System.out.println("list3: " + list3.printList());
		System.out.println("list4: " + list4.printList());
		list3.head = reverseList1(list3.head);
		list4.head = reverseList2(list4.head);
		System.out.println("list3: " + list3.printList());
		System.out.println("list4: " + list4.printList());
	}

	/**
	 * 1.迭代: 时空复杂度O(n)/O(1)
	 * runtime 0ms, beat 100/97
	 */
	public static ListNode reverseList1(ListNode head)
	{
		ListNode prev = null;
		ListNode curr = head;

		//遍历链表, 需要时间为O(n)
		//curr为空时，说明反转已结束
		while (curr != null) {
			head = curr.next;
			curr.next = prev;	//反转箭头
			prev = curr;	//向后移动prev
			curr = head;	//向后移动curr
		}
		return prev;	//反转结束后, prev所在位置应为链表头
	}

	/**
	 * 2.递归: 时空复杂度O(n)/O(n)
	 * runtime 0ms, beat 100/15
	 */
	public static ListNode reverseList2(ListNode head)
	{
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList2(head.next);
		head.next.next = head;	//反转: 令newHead下一位节点为head
		head.next = null;	//设置head下一位为null
		return newHead;
	}
}
