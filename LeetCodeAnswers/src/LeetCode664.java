/* author@ Qian Cai
 * Title@ Strange Printer
 * There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 *time: O(n^3) space: O(n^2)
 */
public class LeetCode664 {
	class Solution {
	    int[][] memo;
	    public int strangePrinter(String s) {
	        int n = s.length();
	        memo = new int[n][n];
	        return dp(s, 0, n-1);
	    }
	    //split at each position
	    public int dp(String s, int i, int j){
	        if (i > j) return 0;
	        if (memo[i][j] == 0){
	            int ans = dp(s, i+1, j) + 1;
	            for (int k = i+1; k <= j; k++){
	                if (s.charAt(i) == s.charAt(k)){
	                    ans = Math.min(ans, dp(s, i, k-1) + dp(s, k+1, j));
	                }
	            }
	            memo[i][j] = ans;
	        }
	        return memo[i][j];
	    }
	}
/*follow up
 * 
 */
}
