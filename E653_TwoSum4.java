import java.util.*;

/**
 * 给定一个二叉搜索树和一个目标结果,
 * 如果 BST 中存在两个元素且它们的和等于给定的目标结果, 则返回 true.
 */
public class E653_TwoSum4
{
	public static void main(String[] args)
	{
		BinaryTree bst = new BinaryTree();
		int[] intArray = {24, 12, 36, 9, 18, 27, 39, 1, 15, 45};
		for (int i : intArray) {
			bst.insert(i);
		}
		System.out.println(findTarget1(bst.root, 24));
		System.out.println(findTarget2(bst.root, 12));
	}

	/**
	 * 1.哈希查找: beat 96/86.
	 *   时间复杂度 O(n), n 是节点的数量. 最坏的情况下, 整棵树被遍历一次.
	 *   空间复杂度 O(n), n 是节点的数量. 最坏的情况下, set 存储 n 个节点的值.
	 */
	public static boolean findTarget1(TreeNode root, int k)
	{
		Set<Integer> set = new HashSet<>();
		return find(root, k, set);
	}

	private static boolean find(TreeNode root, int k, Set<Integer> set)
	{
		if (root == null) {
			return false;
		}
		if (set.contains(k - root.val)) {
			return true;
		}
		set.add(root.val);
		return find(root.left, k, set) || find(root.right, k, set);
	}


	/**
	 * 1.中序遍历双指针: 时空复杂度 O(n)/O(1), beat 5/13
	 */
	public static boolean findTarget2(TreeNode root, int k)
	{
		List<Integer> list = new ArrayList<>();
		inOrder(root, list);
		int left = 0;
		int right = list.size() - 1;
		while (left < right) {
			int sum = list.get(left) + list.get(right);
			if (sum < k) {
				left++;
			} else if (sum > k) {
				right--;
			} else {
				return true;
			}
		}
		return false;
	}

	private static void inOrder(TreeNode root, List<Integer> list)
	{
		if (root != null) {
			inOrder(root.left, list);
			list.add(root.val);
			inOrder(root.right, list);
		}
	}
}
