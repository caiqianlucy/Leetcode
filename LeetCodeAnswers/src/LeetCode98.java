/* author@ Qian Cai
 * Title@ Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 * 
 */
import java.util.*;
public class LeetCode98 {
	//In-order traversal, time: O(n), space: O(n)
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        Integer prev = null;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (prev != null && node.val <= prev) return false;
            prev = node.val;
            node = node.right;
            
        }
        return true;
    }
    //Morris-Traversal
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        TreeNode cur = root;
        TreeNode prev = null, pred = null;
        while (cur != null){
            if (cur.left == null){
                if (prev != null && cur.val <= prev.val) return false;
                prev = cur;
                cur = cur.right;
            }
            else {
                pred = cur.left;
                while (pred.right != null && pred.right != cur){
                    pred = pred.right;
                }
                if (pred.right == null){
                    pred.right = cur;
                    cur = cur.left;
                } else{
                    pred.right = null;
                    if (prev != null && cur.val <= prev.val) return false;
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        return true;
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
