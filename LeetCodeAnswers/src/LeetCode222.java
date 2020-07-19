/* author@ Qian Cai
 * Title@ Count Complete Tree Nodes
 * Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. It can have between 1 and 
2h nodes inclusive at the last level h.
=======================================
 * Solution 1: Two binary search
 * time: O(logn)
 * space: O(1)
 * Solution 2: direct count
 * time: O(n)
 * space: O(logn)
 */
public class LeetCode222 {
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
	class Solution1 {
	    public int countNodes(TreeNode root) {
	        if (root == null) return 0;
	        int d= getDepth(root);
	        if (d == 0) return 1;
	        int left = 1; 
	        int right = (int) Math.pow(2, d);
	        while (left <= right){
	            int mi = left + (right-left)/2;
	            if (exists(root, mi, d)) left = mi+1;
	            else right = mi-1;
	        }
	        //System.out.println(right);
	        return (int)Math.pow(2, d) -1 + right;
	        
	    }
	    public int getDepth(TreeNode root){
	        int d = 0;
	        while (root.left != null){
	            root = root.left;
	            d++;
	        }
	        return d;
	    }
	    public boolean exists(TreeNode node, int idx, int d){
	        int left = 1, right =(int)Math.pow(2, d);
	        //looks where the node belongs to at each depth
	        for (int i = 0; i < d; i++){
	            int mi = left + (right-left)/2;
	            if (idx <= mi){
	                node = node.left;
	                right = mi;
	            } else{
	                node = node.right;
	                left = mi+1;
	            }
	        }
	        
	        return node != null;
	    }
	}
	class Solution2 {
	    public int countNodes(TreeNode root) {
	        return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);
	    }
	}
}
