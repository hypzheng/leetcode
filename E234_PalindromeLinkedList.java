import java.util.ArrayList;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * 已知单链表的头节点，判断一个链表是否为回文链表。
 */
public class E234_PalindromeLinkedList
{
	public static void main(String[] args)
	{
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		int[] digitArray1 = {1, 3, 5, 3, 1};
		int[] digitArray2 = {2, 4, 6, 8};

		for (int i : digitArray1) {
			list1.insertTail(i);
		}
		for (int i: digitArray2) {
			list2.insertHead(i);
		}

		System.out.print("list1: ");
		list1.printList();
		System.out.print("list2: ");
		list2.printList();
		System.out.println("Is list1 palindrome? " + isPalindrome1(list1.head));
		System.out.println("Is list2 palindrome? " + isPalindrome2(list2.head));
	}

	/**
	 * 1.快慢指针: 时空复杂度 O(n)/O(1)
	 * 	 复原链表: beat 97/74
	 * 	 不复原链表: beat 100/89
	 */
	public static boolean isPalindrome1(ListNode head)
	{
		//若链表元素个数为0或1，则必为回文
		if (head == null || head.next == null) {
			return true;
		}

		boolean isPld = true;	//用于复原前半链表
		ListNode prev = null;
		ListNode curr = null;
		ListNode slow = head;
		ListNode fast = head;

		//找出链表的中点: slow每次移动一个节点, fast每次移动两个节点
		while (fast != null && fast.next != null) {
			curr = slow;	//1.更新curr的位置
			slow = slow.next;	//2.移动slow和fast
			fast = fast.next.next;
			curr.next = prev;	//3.令curr的下一位节点为prev, 再更新prev的位置
			prev = curr;
		}

		ListNode midLeft = curr;	//记录前半链表的最末位节点
		ListNode midRight = slow;	//记录后半链表的第一位节点

		//当链表元素个数为奇数时，fast必不为空. 此时的midRight(slow)为链表的中点
		//链表中点无需进行回文判断，因此要将slow更新为后半链表的第二个节点
		if (fast != null) {
			slow = slow.next;	//此时slow为后半链表的第二个节点
		}

		//回文判断. 若为假，则不移动curr和slow, 直接跳出
		while (curr != null && slow != null) {
			if (curr.val != slow.val) {
				isPld = false;
				break;
			}
			curr = curr.next;
			slow = slow.next;
		}

		//复原链表中被反转的部分, 若midLeft为空则整个链表已修复
		while (midLeft != null) {
			ListNode temp = midLeft.next;	//即反转之前的第 midLeft-1 位节点
			midLeft.next = midRight;	//删除midLeft->midLeft-1的link, 修正为midLeft->midRight
			midRight = midLeft;	//midRight向原链表表头方向移动
			midLeft = temp;	//midLeft向原链表表头方向移动
		}
		return isPld;
	}

	/**
	 * 2.数组存储链表值: 时空复杂度 O(n)/O(n), beat 32/34
	 */
	public static boolean isPalindrome2(ListNode head)
	{
		//遍历链表, 并把链表里的值复制进一个数组里: 时间O(n)
		//数组大小和链表元素个数为n: 空间O(n)
		ArrayList<Integer> values = new ArrayList<>();
		ListNode curr = head;
		while (curr != null) {
			values.add(curr.val);
			curr = curr.next;
		}

		//使用双指针判断回文, 执行了O(n/2)次判断: 时间O(n)
		int front = 0;
		int back = values.size() - 1;
		while (front < back) {
			if (!values.get(front).equals(values.get(back))) {
				return false;
			}
			front++;
			back--;
		}
		return true;
	}
}
