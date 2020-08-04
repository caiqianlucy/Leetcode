/* author@ Qian Cai
 * Title@ Cherry Pickup II
 * Solution 1
 * top down dp: 
 * time: O(mn*n) space: O(m*n*n)
 * Solution 2:
 * bottom up dp:
 * time: O(mn*n) space: O(n^n)
 */
import java.util.*;		
public class LeetCode1463 {
	class Solution1{
	    int m, n;
	    int[][] grid;
	    int[][][] dp;
	    public int cherryPickup(int[][] grid) {
	        m = grid.length;
	        n = grid[0].length;
	        this.grid = grid;
	        this.dp = new int[m][n][n];
	        for (int[][] rows: dp){
	            for (int[] row2: rows){
	                Arrays.fill(row2, Integer.MIN_VALUE);
	            }
	        }
	        return helper(0, 0, n-1);
	    }
	    public int helper(int r, int c1, int c2){
	        if (c1 < 0 || c2 < 0 || c1 >= n || c2 >= n) return Integer.MIN_VALUE;
	        if (dp[r][c1][c2] != Integer.MIN_VALUE) return dp[r][c1][c2];
	        int ans = grid[r][c1];
	        if (c1 != c2) ans += grid[r][c2];        
	        if (r == m-1){
	            dp[r][c1][c2] = ans;
	            return ans;
	        }
	        int temp = 0;
	        for (int d1 = -1; d1 <= 1; d1++){
	            for (int d2 = -1; d2 <= 1; d2++){
	                temp = Math.max(temp, helper(r+1, c1+d1, c2+d2));
	            }
	        }
	        dp[r][c1][c2] = ans+temp;
	        return dp[r][c1][c2];
	    }
	}
	class Solution2 {
	    
	    public int cherryPickup(int[][] grid) {
	        int m = grid.length;
	        int n = grid[0].length;
	        int[][] prev = new int[n][n];
	        for (int r = m-1; r >= 0; r--){
	            int[][] cur = new int[n][n];
	            for (int c1 = 0; c1 < n; c1++){
	                for (int c2 = 0; c2 < n; c2++){
	                    int res = grid[r][c1];
	                    if (c2 != c1) res += grid[r][c2];
	                    int temp = 0;
	                    for (int d1 = -1; d1 <= 1; d1++){
	                        for (int d2 = -1; d2 <= 1; d2++){
	                            if (c1 + d1 < 0 || c1 + d1 >= n || c2 + d2 < 0 || c2 + d2 >= n) continue;
	                            temp = Math.max(temp, prev[c1+d1][c2+d2]);
	                        }
	                    }
	                    cur[c1][c2] = res + temp;
	                }
	            }
	            prev = cur;
	        }   
	        return prev[0][n-1];            
	    }    
	}
}
