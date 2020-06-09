/* author@ Qian Cai
 * Title@ Delete Nodes and Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.
 * 
 */
import java.util.*;
public class LeetCode1110 {
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {       
        if (root == null) return new ArrayList();;
        Set<Integer> set = new HashSet();
        for (int n: to_delete) set.add(n);
        List<TreeNode> res = new ArrayList();
        helper(root, set, res);
        if (!set.contains(root.val)) res.add(root);
        return res;
    }
    public void helper(TreeNode root, Set<Integer> set, List<TreeNode> res){
        if (root == null) return;
        helper(root.left, set, res);
        helper(root.right, set, res);
        if (root.left != null && set.contains(root.left.val)){
            root.left = null;
        }
        if (root.right != null && set.contains(root.right.val)){
            root.right = null;
        }
        if (set.contains(root.val)){
            if (root.left != null) res.add(root.left);
            if (root.right != null) res.add(root.right);
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
