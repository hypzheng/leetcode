/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class E104_BT_maxHeight
{
	public static void main(String[] args)
	{
		Tree_BT tree = new Tree_BT();
		int[] array1 = {1, 3, 5, 7, 9, 18, 16, 14, 12, 10};
		for (int i : array1) {
			tree.insert(i);
		}
		System.out.println(maxDepth(tree.root));
	}

	/**
	 * 1.深度优先搜索: beat 100/98.
	 *   时间复杂度: O(n), 其中 n 为二叉树节点的个数, 每个节点在递归中只被遍历一次.
	 *   空间复杂度: O(h), 其中 h 为二叉树的高度.
	 *   递归函数需要栈空间, 而栈空间取决于递归的深度, 因此空间复杂度等价于二叉树的高度.
	 */
	public static int maxDepth(TreeNode root)
	{
		return calcDepth(root);
	}

	private static int calcDepth(TreeNode root)
	{
		if (root != null) {
			int heightL = calcDepth(root.left);
			int heightR = calcDepth(root.right);
			return Math.max(heightL, heightR) + 1;
		}
		return 0;
	}
}
