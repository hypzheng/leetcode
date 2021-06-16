

import java.util.*;
import java.util.LinkedList;

/**
 * Binary tree functions
 */
public class BinaryTree
{
	TreeNode root;    //根节点
	int size;

	/* 构造器 */
	public BinaryTree()
	{
		root = null;
		size = 0;
	}

	/* 插入新节点 */
	public void insert(int item)
	{
		if (root == null) {
			root = new TreeNode(item);
			size++;
		} else {
			insertChild(item, root);
		}
	}

	/* 插入新子节点 (递归) */
	private void insertChild(int item, TreeNode node)
	{
		//允许重复值时, 若新节点值 = 目标节点值, 则插入目标节点的右子节点处
		if (item < node.val) {
			if (node.left == null) {
				node.left = new TreeNode(item);
				size++;
			} else {
				insertChild(item, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new TreeNode(item);
				size++;
			} else {
				insertChild(item, node.right);
			}
		}
	}

	/**
	 * DFS算法 (深度优先): 递归进行前/中/后序遍历
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 二叉树的遍历中每个节点只会被访问一次
	 * 空间复杂度: O(n). 空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)
	 */
	public ArrayList<Integer> DFS_recursive(int type)
	{
		ArrayList<Integer> list = new ArrayList<>();
		TreeNode curr = root;

		//0:前序遍历, 1:中序遍历, 2:后序遍历
		if (type == 0) {
			preOrder_recursive(list, curr);
		} else if (type == 1) {
			inOrder_recursive(list, curr);
		} else if (type == 2) {
			postOrder_recursive(list, curr);
		}
		return list;
	}

	/* 前序遍历: 先访问根，然后访问子树 */
	private void preOrder_recursive(ArrayList<Integer> list, TreeNode curr)
	{
		list.add(curr.val);
		if (curr.left != null) {
			preOrder_recursive(list, curr.left);
		}
		if (curr.right != null) {
			preOrder_recursive(list, curr.right);
		}
	}

	/* 中序遍历: 先访问左（右）子树，然后访问根，最后访问右（左）子树 */
	private void inOrder_recursive(ArrayList<Integer> list, TreeNode curr)
	{
		if (curr.left != null) {
			inOrder_recursive(list, curr.left);
		}
		list.add(curr.val);
		if (curr.right != null) {
			inOrder_recursive(list, curr.right);
		}
//		if (curr != null) {
//			inOrder(list, curr.left);
//			list.add(curr.val);
//			inOrder(list, curr.right);
//		}
	}

	/* 后序遍历: 先访问子树，然后访问根 */
	private void postOrder_recursive(ArrayList<Integer> list, TreeNode curr)
	{
		if (curr.left != null) {
			postOrder_recursive(list, curr.left);
		}
		if (curr.right != null) {
			postOrder_recursive(list, curr.right);
		}
		list.add(curr.val);
	}


	/**
	 * DFS算法 (深度优先): 栈迭代进行前/中/后序遍历
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 二叉树的遍历中每个节点只会被访问一次
	 * 空间复杂度: O(n). 空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)
	 */
	public ArrayList<Integer> DFS_iterative(int type)
	{
		//若二叉树为空, 则直接打印一个空数组
		if (root == null) {
			System.out.println(Arrays.toString(new int[0]));
		}

		ArrayList<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;

		//0:前序遍历, 1:中序遍历, 2:后序遍历
		if (type == 0) {
			preOrder_iterative(list, stack, curr);
		} else if (type == 1) {
			inOrder_iterative(list, stack, curr);
		} else if (type == 2) {
			postOrder_iterative(list, stack, curr);
		}
		return list;
	}

	/* 前序遍历: 先访问根，然后访问子树 */
	private void preOrder_iterative(ArrayList<Integer> list, Stack<TreeNode> stack, TreeNode curr)
	{
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				list.add(curr.val);
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				curr = curr.right;
			}
		}
	}

	/* 中序遍历: 先访问左（右）子树，然后访问根，最后访问右（左）子树 */
	private void inOrder_iterative(ArrayList<Integer> list, Stack<TreeNode> stack, TreeNode curr)
	{
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				list.add(curr.val);
				curr = curr.right;
			}
		}
	}

	/* 后序遍历: 先访问子树，然后访问根 */
	private void postOrder_iterative(ArrayList<Integer> list, Stack<TreeNode> stack, TreeNode curr)
	{
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				list.add(curr.val);
				stack.push(curr);
				curr = curr.right;
			} else {
				curr = stack.pop();
				curr = curr.left;
			}
		}
	}


	/**
	 * BFS算法 (广度优先): 队列迭代进行层次遍历 (先访问离根节点最近的节点)
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 即 BFS 需循环 n 次
	 * 空间复杂度: O(n). 最差情况 (平衡二叉树) 时, 最多有 n/2 个树节点同时在队列中
	 */
	public ArrayList<Integer> BFS_iterative()
	{
		//若二叉树为空, 则直接打印一个空数组
		if (root == null) {
			System.out.println(Arrays.toString(new int[0]));
		}

		ArrayList<Integer> list = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root); //使用offer()添加元素, poll()获取并移除元素: 可通过返回值判断成功与否

		//
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			list.add(curr.val);
			if (curr.left != null) {
				queue.offer(curr.left);
			}
			if (curr.right != null) {
				queue.offer(curr.right);
			}
		}
		return list;
	}

	/* 打印二叉树 */
	public void printBST(ArrayList<Integer> list)
	{
		StringBuilder str = new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i));
			if (i < list.size()-1) {
				str.append(", ");
			}
		}
		str.append("]");
		System.out.println(str);
	}
}
