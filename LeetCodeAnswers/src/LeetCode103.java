/* author@ Qian Cai
 * Title@ Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order 
 * traversal of its nodes' values. (ie, from left to right, 
 * then right to left for the next level and alternate between).
 * Solution:
 * Time: O(n)
 * Space: O(n)
 * BFS
 * 
 */
import java.util.*;
public class LeetCode103 {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();
        List<List<Integer>> res = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root); //bfs level by level travesral
        boolean leftToRight = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList();
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if (leftToRight){
                    level.add(cur.val);
                } else {
                    level.addFirst(cur.val);
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);             
            }
            res.add(new LinkedList<Integer>(level));
            leftToRight = !leftToRight;
        }
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
