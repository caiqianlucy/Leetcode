/* author@ Qian Cai
 * Title@ Binary Tree Maximum Path Sum
 * Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting
 node to any node in the tree along the parent-child connections. The path must
  contain at least one node and does not need to go through the root.
 * 
 * Solution
 * Recursion
 * Time Complexity: O(n)
 * Space complexity: O(h)
 */
public class LeetCode124 {
	int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        helper(root); 
        return res;
    }
    //return max sum from node through only parent to child not left child to right child
    public int helper(TreeNode node){        
        if (node == null) return 0;
        int left_gain = Math.max(0, helper(node.left));
        int right_gain = Math.max(0, helper(node.right));
        
        int maxThroughNode = node.val + left_gain + right_gain;
        res = Math.max(res, maxThroughNode);
        return node.val + Math.max(left_gain, right_gain);
    }
  
     
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
  
}
