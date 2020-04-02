/*author@ Qian Cai
 * Title@ Smallest Subtree with all the Deepest Nodes
 * 
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 */
public class LeetCode865 {
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }
    public Pair dfs(TreeNode node){
        if (node == null) return new Pair(0, null);
        Pair l  = dfs(node.left);
        Pair r = dfs(node.right);
        if (l.depth < r.depth) return new Pair (r.depth+1, r.node);
        if (r.depth < l.depth) return new Pair(l.depth+1, l.node);
        return new Pair(r.depth+1, node);
    }
    private class Pair{
        int depth;
        TreeNode node;
        private Pair(int d, TreeNode n){
            depth = d;
            node = n;
        }
    }
    public class TreeNode {
    	 int val;
    	 TreeNode left;
    	 TreeNode right;
    	 TreeNode(int x) { val = x; }
    }
}
