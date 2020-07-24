/* author@Qian Cai
 * Title@ Count Univalue Subtrees
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.
 * 
 */

public class LeetCode250 {
	public int countUnivalSubtrees(TreeNode root) {
        return helper(root)[0];
    }
    //res[0] uni-value subtree counts, res[1] 0 not uni-val tree, 1 is uni-val tree for cur node
    public int[] helper(TreeNode node){
        int[] res = new int[2];
        if (node == null) return res;
        int[] left = helper(node.left);
        int[] right = helper(node.right);
        res[1] = 1;
        if (node.left != null && (node.left.val != node.val || left[1] == 0)){
            res[1] = 0;
        }
        if (node.right != null && (node.right.val != node.val || right[1] == 0)){
            res[1] = 0;
        }
        res[0] = left[0] + right[0] + res[1];
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
