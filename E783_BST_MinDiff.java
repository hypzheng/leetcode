import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference
 * between the values of any two different nodes in the tree.
 *
 * 给你一个二叉搜索树的根节点 root ，返回树中任意两不同节点值之间的最小绝对差值 。
 */

public class E783_BST_MinDiff
{
	public static void main(String[] args)
	{
		Tree_BT bst = new Tree_BT();
		int[] intArray = {24, 12, 36, 9, 18, 27, 39, 1, 15, 45};
		for (int i : intArray) {
			bst.insert(i);
		}
		System.out.println("中序遍历结果: " + bst.DFS_iterative_inOrder(bst.root));
		System.out.println("最小绝对差为: " + minDiffInBST(bst.root));
		System.out.println("高度: " + bst.getHeight());
	}

	/**
	 * 1.中序遍历是有序的, 因此可以得到一个有序序列
	 *   时空复杂度 O(n)/O(n), beat 100/99
	 */
	public static int minDiffInBST(TreeNode root)
	{
		TreeNode prev = null;
		int ans = Integer.MAX_VALUE;
		Deque<TreeNode> stack = new LinkedList<>();

		while (root != null || !stack.isEmpty())
		{
			//持续遍历到左子树最下端, 同时将每个根节点都保存到栈中
			if (root != null) {
				stack.push(root);
				root = root.left;
			}
			//此时curr为空, 说明curr到达左子树最下端
			else {
				root = stack.pop();	//开始出栈
				if (prev != null) {
					ans = Math.min(Math.abs(prev.val - root.val), ans);
				}
				prev = root;
				root = root.right;	//进入右子树, 开始新一轮左子树遍历
			}
		}
		return ans;
	}
}
