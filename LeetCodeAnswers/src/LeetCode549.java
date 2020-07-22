import LeetCode337.TreeNode;

/* author@ Qian Cai
 * Title@ Binary Tree Longest Consecutive Sequence II
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary 
 * Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] 
and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the 
other hand, the path can be in the child-Parent-child order, where not necessarily be 
parent-child order.
 * 
 */
public class LeetCode549 {
	 int res = 0;
	    public int longestConsecutive(TreeNode root) {
	        
	        helper(root);
	        return res;
	    }
	    public int[] helper(TreeNode node){
	        if (node == null) return new int[]{0, 0};
	        int inc = 1, dec = 1;
	        if (node.left != null){
	            int[] l = helper(node.left);
	            if (node.val == node.left.val + 1){
	                dec = l[1] + 1;
	            } else if (node.val == node.left.val -1){
	                inc = l[0] + 1;
	            }
	        }
	        if (node.right != null){
	            int[] r = helper(node.right);
	            if(node.right.val + 1== node.val){
	                dec = Math.max(dec, r[1] + 1);
	            } else if (node.val == node.right.val - 1){
	                inc = Math.max(inc, r[0] + 1);
	            }
	        }
	        res = Math.max(res, dec + inc - 1);
	        return new int[]{inc, dec};
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
