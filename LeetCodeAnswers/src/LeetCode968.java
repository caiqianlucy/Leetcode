/* author@ Qian Cai
 * Title@ Binary Tree Camera
 * Time: O(n)
 * 
 */
public class LeetCode968 {
	 public int minCameraCover(TreeNode root) {
	        return Math.min(helper(root)[1], helper(root)[2]);
	    }
	    /*res[0], All nodes below are covered, but not the node
	      res[1], All nodes are covered, camer not in the node
	      res[2], All nodes are covered, camer in the node
	    */
	    public int[] helper(TreeNode node){
	        int[] res = new int[3];
	        if (node == null){
	            res[2] = 1001;
	            return res;
	        } 
	        int[] left = helper(node.left);
	        int[] right = helper(node.right);
	        res[0] = left[1] + right[1];
	        int mL12 = Math.min(left[1], left[2]);
	        int mR12 = Math.min(right[1], right[2]);
	        res[1] = Math.min(left[2] + mR12, right[2] + mL12);
	        res[2] = 1 + Math.min(left[0], mL12) + Math.min(mR12, right[0]);
	        
	        return res;
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
