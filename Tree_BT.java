import java.util.*;
import java.util.LinkedList;

/**
 * 二叉树
 */
public class Tree_BT extends Tree
{
	/* 插入新节点 (递归) */
	public void insert(int val)
	{
		if (root == null) {
			root = new TreeNode(val);
			size++;
		} else {
			insertChild(root, val);
		}
	}

	/* 插入新子节点 (递归) */
	private void insertChild(TreeNode node, int val)
	{
		if (node.left == null) {
			node.left = new TreeNode(val);
			size++;
		} else if (node.right == null) {
			node.right = new TreeNode(val);
			size++;
		} else if (node.left.left == null || node.left.right == null) {
			insertChild(node.left, val);
		} else {
			insertChild(node.right, val);
		}
	}

	/* 二叉树高度 */
	public int getHeight()
	{
		return calcHeight(root);
	}

	/* 设: 只存在root节点时高度为0 */
	private int calcHeight(TreeNode node)
	{
		//if限制, 避免出现NullPointerException
		if (node != null) {
			int heightL = calcHeight(node.left);
			int heightR = calcHeight(node.right);
			if (heightL > heightR) {
				return (heightL + 1);
			} else {
				return (heightR + 1);
			}
		}
		return 0;	//此时root为空
	}

	/* 打印二叉树: 数组 */
	public void printTree_list(List<Integer> list)
	{
		StringBuilder str = new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i));
			if (i < list.size() - 1) {
				str.append(", ");
			}
		}
		str.append("]");
		System.out.println(str);
	}

	/* 打印二叉树: 按层 */
	public void printTree_level()
	{
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);

			int countCurr = 1;	//当前层还未打印的节点个数 (根节点已存入queue)
			int countNext = 0;	//下一层还未打印的节点个数

			while (!queue.isEmpty()) {
				TreeNode curr = queue.poll();
				System.out.print(curr.val);
				countCurr--;	//打印一个节点后, count减1

				if (curr.left != null) {
					queue.offer(curr.left);
					countNext++;
				}
				if (curr.right != null) {
					queue.offer(curr.right);
					countNext++;
				}

				if (countCurr == 0) {
					System.out.println();
					countCurr = countNext;
					countNext = 0;
				}
			}
		}
	}
}
