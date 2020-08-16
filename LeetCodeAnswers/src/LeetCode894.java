/* author@ Qian Cai
 * Title@ All Possible Full Binary Trees
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.
 * 
 */
import java.util.*;
public class LeetCode894 {
	public List<TreeNode> allPossibleFBT(int N) {
        Map<Integer, List<TreeNode>> memo = new HashMap();
        return helper(N, memo);
    }
    public List<TreeNode> helper(int N, Map<Integer, List<TreeNode>> memo){
        List<TreeNode> list = new ArrayList();
        if (N == 1){
            list.add(new TreeNode(0));
            return list;
        }
        if (!memo.containsKey(N)){
            
            for (int i = 1; i <= N-2; i++){
                List<TreeNode> left = helper(i, memo);
                List<TreeNode> right = helper(N-i-1, memo);
                if (left.size() != 0 && right.size() != 0){
                    for (TreeNode l: left){
                        for (TreeNode r: right){
                            TreeNode root = new TreeNode(0);
                            root.left = l;
                            root.right = r;
                            list.add(root);
                        }
                    }
                }
            }
            memo.put(N, list);
        }
        return memo.get(N);
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
