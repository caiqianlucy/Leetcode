/* author@ Qian Cai
 * Title@ Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST).
 *  Your iterator will be initialized with the root node
 *   of a BST.

Calling next() will return the next smallest number in 
the BST.
 * 
 * Solution: Inorder traversal to flattern the tree
 * Time: O(n) for initialize, O(1) for next() and hasNext()
 * Space: O(n)
 */
import java.util.*;
public class BSTIterator{
	List<Integer> list;
    int idx;
    public BSTIterator(TreeNode root) {
        list = new ArrayList();
        idx = 0;
        inorder(root);
    }
    public void inorder(TreeNode root){
        if (root == null) return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        return list.get(idx++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (idx >= list.size()) return false;
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
