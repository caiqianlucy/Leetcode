import LeetCode662.TreeNode;

/* author@ Qian Cai
 * Title@ Maximum Binary Tree
 * 
 * 
 */
import java.util.*;
public class LeetCode654 {
	//time: O(N^2)
    public TreeNode constructMaximumBinaryTree(int[] nums) {        
        return helper(nums, 0 , nums.length-1);
    }
    public TreeNode helper(int[] nums, int s, int e){
        if (s > e) return null;
        if (s == e) return new TreeNode(nums[s]);
       
        int max_idx = s;
        for (int i = s; i <= e; i++){
            if (nums[i] > nums[max_idx]){
                max_idx = i;
            }
        }
        TreeNode node = new TreeNode(nums[max_idx]);
        node.left = helper(nums, s, max_idx-1);
        node.right = helper(nums, max_idx+1, e);
        return node;
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
    //time: O(n)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> deque = new LinkedList();
        for (int num: nums){
            TreeNode cur = new TreeNode(num);
            while (!deque.isEmpty() && deque.peekLast().val < cur.val){
                cur.left = deque.removeLast();
            }
            if (!deque.isEmpty()) deque.peekLast().right = cur;
            deque.addLast(cur);
        }
        return deque.getFirst();
    }
}
