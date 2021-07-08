import java.util.Arrays;

public class Case_BST
{
	public static void main(String[] args)
	{
		BinaryTree bst = new BinaryTree();
		int[] intArray = {5, 3, 8, 2, 4, 6, 1, 7};
		for (int i : intArray) {
			bst.insert(i);
		}

		System.out.print("BST原始数组:\t\t\t");
		System.out.println(Arrays.toString(intArray));
		System.out.println();

		System.out.print("DFS递归 - 前序遍历:\t");
		bst.printBST(bst.DFS_recursive(0));
		System.out.print("DFS递归 - 中序遍历:\t");
		bst.printBST(bst.DFS_recursive(1));
		System.out.print("DFS递归 - 后序遍历:\t");
		bst.printBST(bst.DFS_recursive(2));
		System.out.println();

		System.out.print("DFS迭代 - 前序遍历:\t");
		bst.printBST(bst.DFS_iterative_preOrder(bst.root));
		System.out.print("DFS迭代 - 中序遍历:\t");
		bst.printBST(bst.DFS_iterative_inOrder());
		System.out.print("DFS迭代 - 后序遍历1:\t");
		bst.printBST(bst.DFS_iterative_postOrder1());
		System.out.print("DFS迭代 - 后序遍历2:\t");
		bst.printBST(bst.DFS_iterative_postOrder2());
		System.out.println();

		System.out.print("BFS迭代:\t\t\t");
		bst.printBST(bst.BFS_iterative());
	}
}
