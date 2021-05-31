import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary tree functions
 */
public class BinaryTree
{
	TreeNode root;	//根节点
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
		} else {
			insertChild(root, item);
		}
	}

	/* 插入新子节点 (递归) */
	public void insertChild(TreeNode node, int item)
	{
		//新节点值 = 目标节点值时, 插入目标节点的右子节点处
		if (item < node.val) {
			if (node.left == null) {
				node.left = new TreeNode(item);
			} else {
				insertChild(node.left, item);
			}
		} else {
			if (node.right == null) {
				node.right = new TreeNode(item);
			} else {
				insertChild(node.right, item);
			}
		}
	}


	/**
	 * 打印二叉树 (广度优先搜索): 不同层从上至下, 同层从左至右
	 * 时间复杂度: O(n). n 为二叉树的节点数量，即 BFS 需循环 n 次
	 * 空间复杂度: O(n). 最差情况 (平衡二叉树) 时，最多有 n/2 个树节点同时在队列中
	 */
	public void printTreeBFS()
	{
		if (root == null) {
			System.out.println(Arrays.toString(new int[0]));
		}

		int result;
		ArrayList<Integer> list = new ArrayList<>();
		Queue<TreeNode> que = new LinkedList<>();

		que.add(root);


	}

}
