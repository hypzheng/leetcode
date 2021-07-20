import java.util.Arrays;

public class Case_BST
{
	public static void main(String[] args)
	{
		Tree_BST bst1 = new Tree_BST();
		int[] intArray1 = {5, 3, 8, 2, 4, 6, 1, 7};
		for (int i : intArray1) {
			bst1.insert(i);
		}

		Tree_BST bst2 = new Tree_BST();
		int[] intArray2 = {24, 12, 36, 9, 18, 27, 39, 1, 15, 45};
		for (int i : intArray2) {
			bst2.insert(i);
		}

		System.out.print("BST原始数组:\t\t\t");
		System.out.println(Arrays.toString(intArray1));
		System.out.println();

		System.out.print("BFS迭代:\t\t\t");
		bst1.printTree_list(bst2.BFS_iterative());
		System.out.println();

		System.out.print("DFS递归 - 前序遍历:\t");
		bst1.printTree_list(bst2.DFS_recursive(0));
		System.out.print("DFS递归 - 中序遍历:\t");
		bst1.printTree_list(bst2.DFS_recursive(1));
		System.out.print("DFS递归 - 后序遍历:\t");
		bst1.printTree_list(bst2.DFS_recursive(2));
		System.out.println();

		System.out.print("DFS迭代 - 前序遍历:\t");
		bst1.printTree_list(bst1.DFS_iterative_preOrder(bst1.root));
		System.out.print("DFS迭代 - 中序遍历:\t");
		bst1.printTree_list(bst1.DFS_iterative_inOrder(bst1.root));
		System.out.print("DFS迭代 - 后序遍历1:\t");
		bst1.printTree_list(bst1.DFS_iterative_postOrder1(bst1.root));
		System.out.print("DFS迭代 - 后序遍历2:\t");
		bst1.printTree_list(bst1.DFS_iterative_postOrder2(bst1.root));
		System.out.print("DFS迭代 - 后序遍历3:\t");
		bst1.printTree_list(bst1.DFS_iterative_postOrder3(bst1.root));
		System.out.println();
	}
}
