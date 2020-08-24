/* author@ Qian Cai
 * Title@ Unique Binary Search Tree
 * 
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * time: O(n^2) space: O(n)
 */
public class LeetCode96 {
	public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1; //empty 
        dp[1] = 1; //one node
        for (int i = 2; i <= n; i++){
            //left (j) nodes, right (i-1-j) nodes
            for (int j = 0; j <= i-1; j++){
                dp[i] += dp[j]*dp[i-1-j];
            }
        }
        return dp[n];
    }
}
