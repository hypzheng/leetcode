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

	/* 打印二叉树 */
	public void printBST(List<Integer> list)
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

	/*-------------------------------------------------------------------------------*
	 * DFS算法 (深度优先): 递归进行前/中/后序遍历
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 二叉树的遍历中每个节点只会被访问一次
	 * 空间复杂度: O(n). 空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)
	 *-------------------------------------------------------------------------------*/
	public List<Integer> DFS_recursive(int type)
	{
		List<Integer> list = new ArrayList<>();
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

	/* 前序遍历: 先访问根, 然后访问左子树, 最后访问右子树 */
	private void preOrder_recursive(List<Integer> list, TreeNode curr)
	{
		if (curr != null) {
			list.add(curr.val);
			preOrder_recursive(list, curr.left);
			preOrder_recursive(list, curr.right);
		}
	}

	/* 中序遍历: 先访问左子树, 然后访问根, 最后访问右子树 */
	private void inOrder_recursive(List<Integer> list, TreeNode curr)
	{
		if (curr != null) {
			inOrder_recursive(list, curr.left);
			list.add(curr.val);
			inOrder_recursive(list, curr.right);
		}
	}

	/* 后序遍历: 先访问左子树, 然后访问右子树, 最后访问根 */
	private void postOrder_recursive(List<Integer> list, TreeNode curr)
	{
		if (curr != null) {
			postOrder_recursive(list, curr.left);
			postOrder_recursive(list, curr.right);
			list.add(curr.val);
		}
	}


	/*----------------------------------------------------------------------------------------*
	 * DFS算法 (深度优先): 栈迭代进行前/中/后序遍历
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 二叉树的遍历中每个节点只会被访问一次
	 * 空间复杂度: O(n). 即迭代过程中显式栈的开销, 平均情况下为 O(log n), 最坏情况下(树为链状)为 O(n)
	 *----------------------------------------------------------------------------------------*/

	/* 前序遍历: 先访问根, 然后访问左子树, 最后访问右子树 */
	public List<Integer> DFS_iterative_preOrder()
	{
		ArrayList<Integer> list = new ArrayList<>();

		//若二叉树为空, 则打印出空list
		if (root != null) {
			TreeNode curr = root;
			Stack<TreeNode> stack = new Stack<>();

			while (curr != null || !stack.isEmpty())
			{
				//持续遍历到左子树最下端, 同时将每个根节点都保存到栈中
				if (curr != null) {
					list.add(curr.val);
					stack.push(curr);
					curr = curr.left;

				//此时curr为空, 说明curr到达左子树最下端
				} else {
					curr = stack.pop();    //开始出栈
					curr = curr.right;    //进入右子树, 开始新一轮左子树遍历
				}
			}
		}
		return list;
	}

	/* 中序遍历: 先访问左子树, 然后访问根, 最后访问右子树 */
	public List<Integer> DFS_iterative_inOrder()
	{
		ArrayList<Integer> list = new ArrayList<>();

		//若二叉树为空, 则打印出空list
		if (root != null) {
			TreeNode curr = root;
			Stack<TreeNode> stack = new Stack<>();

			while (curr != null || !stack.isEmpty())
			{
				//持续遍历到左子树最下端, 同时将每个根节点都保存到栈中
				if (curr != null) {
					stack.push(curr);
					curr = curr.left;

				//此时curr为空, 说明curr到达左子树最下端
				} else {
					curr = stack.pop();    //开始出栈
					list.add(curr.val);
					curr = curr.right;    //进入右子树, 开始新一轮左子树遍历
				}
			}
		}
		return list;
	}

	/* 后序遍历: 先访问左子树, 然后访问右子树, 最后访问根 */
	/* beat 100/53 */
	public List<Integer> DFS_iterative_postOrder()
	{
		ArrayList<Integer> list = new ArrayList<>();

		if (root != null) {
			TreeNode curr = root;
			TreeNode prev = null;    //上次访问的节点
			Stack<TreeNode> stack = new Stack<>();

			//while里包含if-else = 两个分开的while
			while (curr != null || !stack.isEmpty())
			{
				//持续遍历到左子树最下端, 同时将每个根节点都保存到栈中
				if (curr != null) {
					stack.push(curr);
					curr = curr.left;

				//此时curr为空, 说明curr到达左子树最下端
				} else {
					curr = stack.pop();    //开始出栈

					//若根节点无右子树, 或其右子树已被访问过, 则该根节点才可以被访问
					if (curr.right == null || curr.right == prev) {
						list.add(curr.val);
						prev = curr;
						curr = null;

					//若根节点的左子树为空, 或其左子树刚被访问过, 则需要先进入其右子树. 该根节点再次入栈
					} else {
						stack.push(curr);
						curr = curr.right;
					}
				}
			}
		}
		return list;
	}

	/* 另一种后序遍历: beat 100/68 */
	public List<Integer> DFS_iterative_postOrder1()
	{
		ArrayList<Integer> list = new ArrayList<>();

		if (root != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode node = stack.pop();
				if (node.left != null) {
					stack.push(node.left);    //先将左节点入栈
				}
				if (node.right != null) {
					stack.push(node.right);    //再将右节点入栈
				}
				list.add(0, node.val);    //最后逆序添加节点的值
			}
		}
		return list;
	}

	/* 另一种后序遍历: beat 100/40 */
	public List<Integer> DFS_iterative_postOrder2()
	{
		LinkedList<Integer> list = new LinkedList<>();

		if (root != null) {
			Deque<TreeNode> stack = new LinkedList<>();
			stack.addFirst(root);
			while (!stack.isEmpty()) {
				TreeNode node = stack.removeFirst();
				list.addFirst(node.val);
				if (node.left != null) {
					stack.addFirst(node.left);    //先将左节点入栈
				}
				if (node.right != null) {
					stack.addFirst(node.right);    //再将右节点入栈
				}
			}
		}
		return list;
	}


	/*-----------------------------------------------------------------------*
	 * BFS算法 (广度优先): 队列迭代进行层次遍历 (先访问离根节点最近的节点)
	 * 时间复杂度: O(n). n 为二叉树的节点数量, 即 BFS 需循环 n 次
	 * 空间复杂度: O(n). 最差情况 (平衡二叉树) 时, 最多有 n/2 个树节点同时在队列中
	 *-----------------------------------------------------------------------*/
	public List<Integer> BFS_iterative()
	{
		List<Integer> list = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();

		//若二叉树为空, 则打印出空list
		if (root != null) {
			queue.offer(root); //使用offer()添加元素, poll()获取并移除元素: 可通过返回值判断成功与否
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
		}
		return list;
	}
}
