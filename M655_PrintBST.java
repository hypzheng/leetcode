import java.util.ArrayList;
import java.util.Arrays;
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

public class M655_PrintBST
{
	public static void main(String[] args)
	{
		BinaryTree bst = new BinaryTree();
		int[] intArray = {5, 3, 8, 2, 4, 6, 1, 7};
		for (int i : intArray) {
			bst.insert(i);
		}

		System.out.println(3<<4);
	}

	/**
	 *
	 * 矩阵size: 行数 = height, 列数 = 2^height - 1
	 */
	public static List<List<String>> printTree(TreeNode root)
	{
		List<List<String>> table = new ArrayList<>();

		//矩阵size: 行数 = 树高度, 列数 = 1 x 2^树高度 - 1
		int numRows = getHeight(root);
		int numCols = (1 << numRows) - 1;

		String[][] matrix = new String[numRows][numCols];

		for (int i = 0; i < matrix.length; i++) {

		}


		return table;
	}

	/**/
	private static void fillMatrix(TreeNode root, String[][] matrix, int row, int left, int right)
	{
		for (String[] strings : matrix) {
			Arrays.fill(strings, "");
		}

		if (root != null) {
			matrix[row][(left + right)/2] = "" ;
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
