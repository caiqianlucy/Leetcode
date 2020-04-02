/*author@ Qian Cai
 * Title@ Number of Dice Rolls With Target Sum
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

 *Time: O(d*f*target) 
 */
public class LeetCode1155 {
	public int numRollsToTarget(int d, int f, int target) {
        long[][] dp = new long[d+1][target+1];
        int MOD = 1000000007;
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++){
           for (int j = 1; j <= target; j++){
               if (i*f < j) continue;
               for (int k = 1; k <= Math.min(j, f); k++){
                 dp[i][j] += dp[i-1][j-k];
                 dp[i][j] %= MOD;
                }
           }
        }
        return (int)dp[d][target];
      
    }
}
