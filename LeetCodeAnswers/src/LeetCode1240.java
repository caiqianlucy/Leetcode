/* author@ Qian Cai
 * Title@ Tilling a Rectangle with the Fewest Squares
 * Solution: O(mn(Math.min(m, n)^2)
 * 
 */
import java.util.*;
public class LeetCode1240 {
	public int tilingRectangle(int n, int m) {
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        return helper(m, n, dp);
    }
    
    public int helper(int m, int n, int[][] dp){
        if (m * n == 0) return 0;
        if (m == n) return 1;
        if (m == 1 && n == 1) return Math.max(m, n);
        if (dp[m][n] != Integer.MAX_VALUE) return dp[m][n];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.min(m, n); i++){
            res = Math.min(res, Math.min(1 + helper(m-i, n, dp) + helper(i, n-i, dp), 1 + helper(m-i, i, dp) + helper(m, n-i, dp)));
            for (int j = 1; j <= Math.min(n-i, i); j++){
                res = Math.min(res, 2 + helper(i-j, n-i, dp) + helper(m-i+j, n-i-j, dp)+ helper(m-i, i+j, dp));
            }
            for (int j = 1; j <= Math.min(m-i, i); j++){
                res = Math.min(res, 2 + helper(m-i, i-j, dp) + helper(m-i+j, n-i, dp) + helper(m-i-j, n-i+j, dp));
            }
        }
        dp[m][n] = res;
        return res;
    }
}
