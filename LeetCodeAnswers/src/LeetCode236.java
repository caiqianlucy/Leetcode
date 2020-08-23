/* author@ Qian Cai
 * Title@ Lowest Common Ancestor of Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 */
public class LeetCode236 {
	TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return ans;
    }
    public boolean[] helper(TreeNode root, TreeNode p, TreeNode q){
        boolean[] res = new boolean[2]; //res[0]: root contains p, res[1]: root contains q
        if (root == null) return res;
        if (root == p) res[0] = true;
        if (root == q) res[1] = true;
        boolean[] left = helper(root.left, p, q);
        boolean[] right = helper(root.right, p, q);
        if (left[0] || right[0]) res[0] = true;
        if (left[1] || right[1]) res[1] = true;
        if (res[0] && res[1] && (!left[0] || !left[1]) && (!right[0] || !right[1])) ans = root;
        return res;
    }
    public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  }
}
