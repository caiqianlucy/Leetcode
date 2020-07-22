/* author@ Qian Cai
 * Title@ House Robber III
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 
 */
import java.util.*;
public class LeetCode337 {
	public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(helper(root)[0], helper(root)[1]);
    }
    public int[] helper(TreeNode node){
        int[] res = new int[2]; //res[0] max money get rob the node, res[1] max money can get without rob the node;
        if (node == null){
            return res;
        }
        int[] left = helper(node.left);
        int[] right = helper(node.right);
        res[0] = node.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
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
