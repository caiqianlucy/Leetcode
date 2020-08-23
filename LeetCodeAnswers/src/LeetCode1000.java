/* author@ Qian Cai
 * Title@ Minimum Cost to Merge Stones
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 * 
 */
public class LeetCode1000 {
	public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n-1)%(K-1) != 0) return -1;
        int[][] dp = new int[n][n]; //dp[i][j] is the cost to combine stones[i to j](inclusive)
        //for (int i = 0; i < n; i++) dp[i][i] = stones[i];
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) preSum[i] = preSum[i-1] + stones[i];
        return helper(preSum, stones, K, dp, 0, n-1);
    }
    public int helper(int[] preSum, int[] stones, int K, int[][] dp, int left, int right){
        if (right -left +1 < K) return 0; //keeps the stone without merging
        if (right-left+1 == K){
            dp[left][right] = preSum[right] - (left == 0? 0: preSum[left-1]);
            return dp[left][right];
        } 
        if (dp[left][right] != 0) return dp[left][right];
        int res = Integer.MAX_VALUE;
        for (int k = left; k < right; k += K-1){
            res = Math.min(helper(preSum, stones, K, dp, left, k) + helper(preSum, stones, K, dp, k+1, right), res);
        }
        //perform final combine of the whole region
        if ((right-left)%(K-1) == 0) res += preSum[right] - (left == 0 ? 0: preSum[left-1]);
        dp[left][right] = res;
        
        return dp[left][right];
        
    }
}
