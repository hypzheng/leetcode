/**
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements in the BST
 * such that their sum is equal to the given target.
 *
 * 给定一个二叉搜索树和一个目标结果，
 * 如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class E653_TwoSum4
{
	public static void main(String[] args)
	{
		BinaryTree bst = new BinaryTree();
		int[] intArray = {5, 3, 8, 2, 4, 6, 1, 7};
		for (int i : intArray) {
			bst.insert(i);
		}

		bst.printBST(bst.BFS_iterative());
		bst.printBST(bst.DFS_recursive(2));
		bst.printBST(bst.DFS_iterative(2));
	}

	/**
	 * 1.指针: 时空复杂度 O(n)/O(n), beat 32/34
	 *   个是
	 */
	public boolean findTarget1(TreeNode root, int k)
	{


		return true;
	}

	/**
	 *
	 */
	public boolean findTarget2(TreeNode root, int k)
	{


		return true;
	}
}
