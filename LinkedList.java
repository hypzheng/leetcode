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
		head = null;	//头节点
		tail = null;	//尾节点
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


	/* 删除链表中的一个节点，并返回到该节点 */
	public int delete(int item)
	{
		ListNode prev = null;
		ListNode curr = head;

		return 0;
	}

	public void clearList()
	{
		ListNode prev = null;
		ListNode curr = head;
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

	/* 生成新的反转链表，原链表无改动 */
	public LinkedList newReversedList()
	{
		LinkedList list = new LinkedList();
		ListNode prev = null;
		ListNode curr = head;
		ListNode temp;	//哑节点

		//curr为空时，说明反转已结束
		while (curr != null) {
			temp = curr.next;	//使用临时节点temp来标记curr下一位节点
			curr.next = prev;	//反转箭头
			prev = curr;	//向后移动prev
			curr = temp;	//向后移动curr
		}
		list.head = prev;	//反转结束后, prev所在位置应为新链表头
		return list;
	}

	/* 使链表能够按格式打印至控制台 */
	public String toString()
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
		return str.toString();
	}
}
