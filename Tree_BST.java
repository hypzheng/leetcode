/**
 * 二叉搜索树
 * 不允许有重复的值
 * 左子树节点值 < 右子树节点值
 */
public class Tree_BST extends Tree_BT
{
	/* 插入新节点 (递归) */
	public void insert(int val)
	{
		if (root == null) {
			root = new TreeNode(val);
			size++;
		} else {
			insertChild(val, root);
		}
	}

	/* 插入新子节点 (递归) */
	private void insertChild(int val, TreeNode node)
	{
		if (val < node.val) {
			if (node.left == null) {
				node.left = new TreeNode(val);
				size++;
			} else {
				insertChild(val, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new TreeNode(val);
				size++;
			} else {
				insertChild(val, node.right);
			}
		}
	}

	/* 插入新节点 (迭代) */
	public void insert_iter(int val)
	{
		if (root == null) {
			root = new TreeNode(val);
			size++;
		} else {
			TreeNode curr = root;
			while (true) {
				if (val < curr.val) {
					if (curr.left == null) {
						curr.left = new TreeNode(val);
						size++;
						break;
					} else {
						curr = curr.left;
					}
				} else {
					if (curr.right == null) {
						curr.right = new TreeNode(val);
						size++;
						break;
					} else {
						curr = curr.right;
					}
				}
			}
		}
	}
}
