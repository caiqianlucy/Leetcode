//Time, Space: O(n)
public class LeetCode1120 {
	double maxAverage;
    public double maximumAverageSubtree(TreeNode root) {
        maxAverage = 0.0;
        helper(root);
        return maxAverage;
    }
    public double[] helper(TreeNode node){
        double[] res = new double[2];
        if (node == null) return res;
        if (node.left == null && node.right == null){
            res[0] = (double)node.val;
            res[1] = 1.0;
        }
        double[] left = helper(node.left);
        double[] right = helper(node.right);
        res[1] = left[1] + right[1] + 1.0;
        res[0] = (node.val + left[0]*left[1] + right[0]*right[1])/(res[1]);
        maxAverage = Math.max(maxAverage, res[0]);
        return res;
    }
    /*
     * Definition for a binary tree node.
     */
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
}
