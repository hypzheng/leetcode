import java.util.ArrayList;
import java.util.List;

/**
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 *
 * 1.行数 m 应当等于给定二叉树的高度。
 * 2.列数 n 应当总是奇数。
 * 3.根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。
 *   根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。
 *   你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。
 *   即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。
 *   然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 4.每个未使用的空间应包含一个空的字符串""。
 * 5.使用相同的规则输出子树。
 */

public class M655_BT_print2DArray
{
	public static void main(String[] args)
	{
		Tree_BT bst = new Tree_BT();
		int[] intArray = {5, 3, 8, 2, 4, 6, 1, 7};
		for (int i : intArray) {
			bst.insert(i);
		}

		List<List<String>> test = printTree(bst.root);
		for (List<String> row : test) {
			System.out.println(row);
		}
	}

	/**
	 * 1.递归: beat 100/50
	 *   时间复杂度 O(h * 2^h), 其中 h 是树的高度，填充长度为 h * (2^h-1) 的 List
	 *   空间复杂度 O(h * 2^h), List 长度为 h * (2^h-1)
	 */
	public static List<List<String>> printTree(TreeNode root)
	{
		//矩阵size: 行数 = 树高度, 列数 = 1 x 2^树高度 - 1
		int numRows = getHeight(root);
		int numCols = (1 << numRows) - 1;

		//初始化List, 填充空字符串
		List<List<String>> table = new ArrayList<>(numRows);
		for (int i = 0; i < numRows; i++) {
			List<String> rows = new ArrayList<>(numCols);
			for (int j = 0; j < numCols; j++) {
				rows.add("");
			}
			table.add(rows);
		}

		//填充数字
		fillMatrix(table, root, 0, 0, numCols);
		return table;
	}

	/* 二分查找中位, 存入二维数组 */
	private static void fillMatrix(List<List<String>> matrix, TreeNode root, int row, int lo, int hi)
	{
		if (root != null) {
			int mid = (lo + hi) / 2;
			matrix.get(row).set(mid, Integer.toString(root.val));

			fillMatrix(matrix, root.left, row + 1, lo, mid);
			fillMatrix(matrix, root.right, row + 1, mid, hi);
		}
	}

	/* 二维数组行数 m = 二叉树的高度 */
	private static int getHeight(TreeNode root)
	{
		if (root != null) {
			return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		}
		return 0;
	}
}
