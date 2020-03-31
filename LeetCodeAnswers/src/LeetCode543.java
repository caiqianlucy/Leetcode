/*author@ Qian Cai
 * Title@ Diameter of Binary Tree
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * Time, Space: O(n)
 */
public class LeetCode543 {
	int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }
    private int depth(TreeNode node){
        if (node == null) return -1;
        int l = depth(node.left);
        int r = depth(node.right);
        ans = Math.max(ans, l+r+2);
        return Math.max(l, r)+1;
    }
    public class TreeNode {
    	 int val;
    	 TreeNode left;
    	 TreeNode right;
    	 TreeNode(int x) { val = x; }
    	 }
}
