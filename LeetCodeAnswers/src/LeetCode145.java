/* author@ Qian Cai
 * Title@ Binary Tree Postorder Traversal
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 */
import java.util.*;
public class LeetCode145 {
	//using stack
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
		Stack<TreeNode> stack = new Stack();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null){
			while (cur != null) {
if (cur.right != null) stack.push(cur.right);
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (!stack.isEmpty() && cur.right == stack.peek()){
				stack.pop();
				stack.push(cur);
				cur = cur.right;
			} else {
				res.add(cur.val);
                cur = null;
			}
		}
		return res;
    }
	//morris traversal
	class Solution {
	    public List<Integer> postorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList();
			TreeNode dummy = new TreeNode(0);
			dummy.left = root;
			TreeNode cur = dummy;
			TreeNode pre;
			while (cur != null){
				if (cur.left == null){
					cur = cur.right;
				} 
				else {
					pre = cur.left;
					while (pre.right != null && pre.right != cur){
						pre = pre.right;
					}
					if (pre.right == null){
						pre.right = cur;
						cur = cur.left;
					}  else {
						TreeNode first = cur.left;
						pre.right = null;
						LinkedList<Integer> temp = new LinkedList();
						while (first != null){
							temp.addFirst(first.val);
							first = first.right;
						}
						for (int i: temp) res.add(i);
						cur = cur.right;
					}
				}
			}
			return res;

	    }
	}
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode() {}
		      TreeNode(int val) { this.val = val; }
		      TreeNode(int val, TreeNode left, TreeNode right) {
		          this.val = val;
		          this.left = left;
		          this.right = right;
		      }
		  }
}
