/* author@ Qian Cai
 * Title@ Perfect Squares
 * Given a positive integer n, find the least number of perfect square numbers 
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *  Time: O(n^1.5)
 */
import java.util.*;
public class LeetCode279 {
	public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++){
            for (int j = 1; j*j <= i; j++){
                dp[i] = Math.min(dp[i], 1 + dp[i-j*j]);
            }
        }
        return dp[n];
    }
}
