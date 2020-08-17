/* author@ Qian Cai
 * Title@ Maximum Level Sum of a Binary Tree
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 */
public class LeetCode1161 {
	class Solution {
	    //bfs
	    public int maxLevelSum(TreeNode root) {
	        int sum = Integer.MIN_VALUE;
	        int res = -1;
	        Queue<TreeNode> queue = new LinkedList();
	        queue.add(root);
	        int level = 1;
	        while (!queue.isEmpty()){
	            int size = queue.size();
	            int temp = 0;
	            for (int i = 0; i < size; i++){
	                TreeNode cur = queue.poll();
	                temp += cur.val;
	                if (cur.left != null) queue.add(cur.left);
	                if (cur.right != null) queue.add(cur.right);
	            }
	            if (temp > sum){
	                sum = temp;
	                res = level;
	            }
	            level++;
	        }
	        return res;
	        
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
