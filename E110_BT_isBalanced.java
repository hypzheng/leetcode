/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */
public class E110_BT_isBalanced
{
	public static void main(String[] args)
	{
		Tree_BST tree = new Tree_BST();
		int[] array1 = {9, 3, 21, 18, 15, 33, 44, 55};
		for (int i : array1) {
			tree.insert(i);
		}
		System.out.println(tree.BFS_iterative());
		System.out.println(isBalanced(tree.root));
	}

	/**
	 * 1.自底向上的递归
	 *   时间复杂度: O(n), 每个节点的计算高度和判断是否平衡都只需要处理一次, 最坏情况下需要遍历二叉树中的所有节点.
	 *   空间复杂度: O(n), 空间复杂度主要取决于递归调用的层数, 递归调用的层数不会超过 n.
	 *   n 是二叉树中的节点个数, beat 100/100.
	 */
	public static boolean isBalanced(TreeNode root)
	{
		return balHeight(root) >= 0;
	}

	private static int balHeight(TreeNode root)
	{
		if (root == null) {
			return 0;
		}
		int heightL = balHeight(root.left);
		int heightR = balHeight(root.right);

		//如果存在一棵子树不平衡, 则令树高度 = -1
		if (Math.abs(heightL - heightR) > 1 || heightL == -1 || heightR == -1) {
			return -1;
		} else {
			return Math.max(heightL, heightR) + 1;
		}
	}
}
