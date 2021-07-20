import java.util.*;
import java.util.LinkedList;

/**
 * 给你一个二叉树，请你返回其按层序遍历得到的节点值（即逐层地，从左到右访问所有节点）。
 */
public class M102_BT_print2DList
{
	public static void main(String[] args)
	{
		Tree_BT tree = new Tree_BT();
		int[] array1 = {1, 3, 5, 7, 9, 18, 16, 14, 12, 10};
		for (int i : array1) {
			tree.insert(i);
		}
		List<List<Integer>> matrix1 = levelOrder1(tree.root);
		List<List<Integer>> matrix2 = levelOrder2(tree.root);
		System.out.println(matrix1);
		System.out.println(matrix2);
	}

	/**
	 * 1.迭代: 广度优先遍历, beat 95/99
	 *   时间复杂度: O(n), n 为二叉树的节点总数, 每个点进队出队各一次
	 *   空间复杂度: O(n), 队列中元素的个数不超过 n 个
	 */
	public static List<List<Integer>> levelOrder1(TreeNode root)
	{
		List<List<Integer>> matrix = new ArrayList<>();
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);	//将根节点放入队列中, 然后不断遍历队列

			while (!queue.isEmpty()) {
				List<Integer> list = new ArrayList<>();
				int levelSize = queue.size();	//获取本层的节点个数

				//将队列中的全部元素 (本层节点) 取出并存入临时list
				for (int i = 0; i < levelSize; i++) {
					TreeNode curr = queue.remove();
					list.add(curr.val);
					if (curr.left != null) {
						queue.offer(curr.left);
					}
					if (curr.right != null) {
						queue.offer(curr.right);
					}
				}
				matrix.add(list);
			}
		}
		return matrix;
	}

	/**
	 * 2.递归: 深度优先遍历 (前序), beat 100/89
	 *   时间复杂度: O(n), n 为二叉树的节点总数
	 * 	 空间复杂度: O(h), h 为二叉树的高度
	 */
	public static List<List<Integer>> levelOrder2(TreeNode root)
	{
		List<List<Integer>> list = new ArrayList<>();
		dfs_pre(list, root, 0);
		return list;
	}

	private static void dfs_pre(List<List<Integer>> matrix, TreeNode node, int level)
	{
		if (node != null) {
			if (matrix.size() == level) {
				List<Integer> list = new ArrayList<>();
				matrix.add(list);	//此时matrix的size = level+1
			}
			matrix.get(level).add(node.val);	//把node的值填入matrix的第level个list里

			dfs_pre(matrix, node.left, level + 1);
			dfs_pre(matrix, node.right, level + 1);
		}
	}
}
