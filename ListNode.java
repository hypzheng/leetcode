/**
 *  Singly-linked list
 */
public class ListNode
{
	ListNode next;	//下一个节点
	int val;	//节点存储的值

	public ListNode() {}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
