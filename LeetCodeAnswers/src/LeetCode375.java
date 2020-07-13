/* author@ Qian Cai
 * Title@ Guess Number Higher or Lower II
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * 
 */
import java.util.*;
public class LeetCode375 {
	//time: O(n^3) space: O(n^2)
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1]; //the max cost to guess from i to j region 
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        return helper(1, n, dp);
    }
    public int helper(int start, int end, int[][] dp){        
        if (start >= end){
              return 0;
        }
        if (start == end-1){
            dp[start][end] = Math.min(start, end);
            return dp[start][end];
        }
        if (dp[start][end] != Integer.MAX_VALUE) return dp[start][end];
        int res = Integer.MAX_VALUE;
        for (int i = (start+end)/2; i <= end; i++){
            res = Math.min(res, i + Math.max(helper(start, i-1, dp), helper(i+1, end, dp)));
        }
        dp[start][end] = res;
        //System.out.println("start " + start + " end: " + end + " res: " + res);
        return res;
    }
}
