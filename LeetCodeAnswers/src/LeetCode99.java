/* author@ Qian Cai
 * Title@ Recover Binary Search Tree
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Solution:
 * Morris Traversal
 */
public class LeetCode99 {
	 public void recoverTree(TreeNode root) {
	        TreeNode first = null, second = null;
	        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
	        TreeNode cur = root;
	        while (cur != null){
	            TreeNode node = cur.left;
	            if (node != null){
	                while (node.right != null && node.right != cur)                {
	                    node = node.right;
	                }
	                if (node.right == null){
	                    node.right = cur;
	                    cur = cur.left;    
	                    continue;
	                } else {
	                    node.right = null;
	                    
	                }
	            } 
	            
	            if (cur.val < pre.val){
	                if (first == null) first = pre;
	                second = cur;
	            }
	            pre = cur;
	            cur = cur.right;
	        }
	        int temp = first.val;
	        first.val = second.val;
	        second.val = temp;
	 }
	 public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
}
