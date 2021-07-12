/**
 *  Singly-linked list functions
 */
public class LinkedList
{
	ListNode head;	//头节点
	ListNode tail;	//尾节点
	int size;

	/* 构造器 */
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/* 在链表头位置插入新节点(类似栈) */
	public void insertHead(int item)
	{
		//实例化新节点，值为item，其下一位节点为头节点
		head = new ListNode(item, head);
		size++;

		//向空链表中插入新节点时，确保尾节点的位置
		if (size == 1) {
			tail = head;
		}
	}

	/* 在链表尾位置插入新节点(类似队列) */
	public void insertTail(int item)
	{
		//实例化新节点，值为item，其下一位节点为null
		ListNode newNode = new ListNode(item);

		/* 此处判断链表为空，也可用 head == null 或 tail == null */
		if (size == 0) {	//链表为空，所以新节点即头节点
			head = newNode;
		} else {
			tail.next = newNode;	//链表非空，新节点为尾节点的下一位节点
		}
		tail = newNode;	//此时新节点成为新的尾节点
		size++;
	}

	/* 搜索链表中的某个元素 */
	public boolean search(int key)
	{
		ListNode curr = head;
		while (curr != null) {
			if (curr.val == key) {
				size--;
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	/* 删除链表中的某个元素 */
	public boolean deleteData(int key)
	{
		ListNode prev = null;
		ListNode curr = head;

		//curr为空说明链表为空, 直接返回假
		while (curr != null) {
			//当curr所在节点值是需要删除的元素时, 重做link
			if (curr.val == key) {
				if (prev == null) {
					head = curr.next;
				} else {
					prev.next = curr.next;
				}
				size--;
				return true;
			}
			//当curr所在节点值不是需要删除的元素时, 继续遍历
			prev = curr;
			curr = curr.next;
		}
		return false;
	}

	/* 删除链表中的第n个节点 */
	public boolean deleteIndex(LinkedList list, int num)
	{
		ListNode prev = null;
		ListNode curr = head;

		//若要删除的项数大于链表元素个数-1，直接返回假
		if (num < list.size) {
			for (int i = 0; i < num; i++) {
				prev = curr;
				curr = curr.next;
			}
			if (prev == null) {
				head = curr.next;
			} else {
				prev.next = curr.next;
			}
			size--;
			return true;
		}
		return false;
	}

	/* 删除链表中所有节点 */
	public void clearList()
	{
		ListNode curr = head;
		while (curr != null) {
			head = curr.next;
			curr.next = null;
			curr = head;
			size--;
		}
	}

	/* 反转链表，原链表被改动 */
	public void reverseList()
	{
		ListNode prev = null;
		ListNode curr = head;
		ListNode temp;	//哑节点

		//curr为空时，说明反转已结束
		while (curr != null) {
			temp = curr.next;	//使用临时节点temp来标记curr下一位节点
			curr.next = prev;	//反转箭头
			prev = curr;	//prev向表尾移动
			curr = temp;	//curr向表尾移动
		}
		this.head = prev;	//反转结束后, prev所在位置应为链表头
	}

	/* 打印链表至控制台 */
	public void printList()
	{
		StringBuilder str = new StringBuilder("[");
		ListNode curr = head;
		while (curr != null) {
			str.append(curr.val);
			if (curr.next != null) {
				str.append(", ");
			}
			curr = curr.next;
		}
		str.append("]");
		System.out.println(str);
//		return str.toString();
	}
}
