/* author@ Qian Cai
 * Title@ Maximum Width of Binary Tree
 * Time: O(n) space: O(n)
 * BFS
 * 
 */
import java.util.*;
public class LeetCode662 {
	public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(root, 0));
        while (!queue.isEmpty()){
            int size = queue.size();
            Integer first = null, last = null;
            for (int i = 0; i < size; i++){
                Node cur = queue.poll();
                if (i == 0) first = cur.i;
                if (i == size-1) last = cur.i;
                if (cur.t.left != null){
                    queue.add(new Node(cur.t.left, 2*cur.i));
                }
                if (cur.t.right != null){
                    queue.add(new Node(cur.t.right, 2*cur.i + 1));
                }
            }
            res = Math.max(res, last-first+1);
        }
        return res;
    }
    class Node{
        TreeNode t;
        int i;
        Node(TreeNode node, int idx){
            t = node;
            i = idx;
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
