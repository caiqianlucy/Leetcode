/* author@ Qian Cai
 * Title@ Distribute Coin in Binary Tree
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.
 */
public class LeetCode979 {
	public int distributeCoins(TreeNode root) {
        int[] res = helper(root); //res[0] total moves from node, res[1]: total coins in this subtree,  res[2] total nodes in this subtree;
        return res[0];
    }
    public int[] helper(TreeNode node){
        int[] res = new int[3];
        if (node == null) return res;
        int[] l = helper(node.left);
        int[] r = helper(node.right);
        res[0] = l[0] + r[0] + Math.abs(l[1] - l[2]) + Math.abs(r[1]-r[2]);
        res[1] = l[1] + r[1] + node.val;
        res[2] = l[2] +  r[2]  + 1;
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
