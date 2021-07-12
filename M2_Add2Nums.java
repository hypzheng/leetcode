/**
 * 给你两个非空链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class M2_Add2Nums
{
	public static void main(String[] args)
	{
		int[] num1 = {4, 3, 2, 1};
		int[] num2 = {6, 6, 6};
		int[] num3 = {9, 0};
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		LinkedList list3 = new LinkedList();
		initialList(list1, num1);
		initialList(list2, num2);
		initialList(list3, num3);

		LinkedList list4 = new LinkedList();
		list4.head = add2Nums(list1.head, list2.head);
		list4.printList();
	}

	/**
	 * 1.使用两个预先指针来构造新链表: beat 100/90
	 *   时间复杂度: O(max(m,n)). 其中 m 和 n 分别为两个链表的长度. 遍历每个链表的节点需要 O(1).
	 *   空间复杂度: O(1). 返回值不计入空间复杂度.
	 *   两个参数 l1 和 l2 初始为两个链表的头节点.
	 */
	public static ListNode add2Nums(ListNode l1, ListNode l2)
	{
		ListNode head = null;	//用于标记链表头节点
		ListNode tail = null;	//用于给链表增加节点
		int carry = 0;	//进位的值

		while (l1 != null || l2 != null)
		{
			//若节点为null, 令其值为0
			int digit1 = (l1 == null) ? 0 : l1.val;
			int digit2 = (l2 == null) ? 0 : l2.val;

			int sum = digit1 + digit2 + carry;	//此时sum为两个数字之和的真实值
			carry = (sum > 9) ? 1 : 0;	//sum > 9说明和为两位数, 取1

			//head为空时, l1和l2位于头节点
			if (head == null) {
				head = tail = new ListNode(sum % 10);
			} else {
				tail.next = new ListNode(sum % 10);
				tail = tail.next;
			}

			//继续遍历两个链表
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}

		//若存在进位值, 令其为新链表的尾节点
		if (carry > 0) {
			tail.next = new ListNode(carry);
		}
		return head;
	}

	private static void initialList(LinkedList list, int[] nums)
	{
		System.out.print("原数字:\t");
		for (int num : nums) {
			list.insertHead(num);
			System.out.print(num);
		}
		System.out.print("\n链表:\t");
		list.printList();
		System.out.println();
	}
}
