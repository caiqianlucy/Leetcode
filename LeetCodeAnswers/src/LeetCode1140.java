/* author@ Qian Cai
 * Title@ Stone Game II
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 * 
 */
public class LeetCode1140 {
	public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] presum = new int[n];
        presum[0] = piles[0];
        int[][] memo = new int[n][n];
        for (int i = 1; i < n; i++) presum[i] = presum[i-1] + piles[i];
        return helper(piles, 0, 1, presum, memo);
    }
    public int helper(int[] piles, int start, int M, int[] presum, int[][] memo){
        int sum = presum[piles.length-1] - (start == 0 ? 0: presum[start-1]);
        if (piles.length - start  <= 2*M){        
            return sum;
        }
        if (memo[start][M] != 0) return memo[start][M];
        int res = 0;
        for (int i = start; i <= Math.min(start + 2*M -1, piles.length); i++){        
            int X = i - start + 1;
            res = Math.max(res, sum - helper(piles, i+1, Math.max(X, M), presum, memo));
        }
        memo[start][M] = res;
        return res;
    }
}
