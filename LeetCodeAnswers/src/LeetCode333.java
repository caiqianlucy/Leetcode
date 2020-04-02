
/*author@ Qian Cai
 * Title@ Largest BST Subtree
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

 *Time: O(n) Space: O(1)
 */
public class LeetCode333 {
	int res = 0;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return res;
        helper(root); //arr[0], number of nodes in root, -1 means not bst, arr[1] means minimum value of nodes in root, arr[2] represents maximum value of nodes in root
        return res;
    }
    //arr[0], number of nodes in root, -1 means not bst,0 means null node,  arr[1] means minimum value of nodes in root, arr[2] represents maximum value of nodes in root
    public int[] helper(TreeNode root){
        if (root == null) return new int[]{0, 0, 0};
        if (root.left == null && root.right == null){
            res = Math.max(res, 1);
            return new int[]{1, root.val, root.val};
        } 
        int left[] = helper(root.left);
        int right[] = helper(root.right);
        if (left[0] == -1 || right[0] == -1 || (left[0] != 0 && left[2] >= root.val) || (right[0] != 0 && right[1] <= root.val))  return new int[]{-1, 0, 0};
        int[] arr = new int[]{left[0] + right[0] + 1, left[0] == 0 ? root.val:left[1], right[0] == 0 ? root.val:right[2]};
        res = Math.max(res, arr[0]);
        return arr;
    }
    public class TreeNode {
   	 int val;
   	 TreeNode left;
   	 TreeNode right;
   	 TreeNode(int x) { val = x; }
   }
}
