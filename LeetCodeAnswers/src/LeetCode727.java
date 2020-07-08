/* author@ Qian Cai
 * Title@ Minimum Window Subsequence
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 * time: O(mn) space: O(mn)
 */
import java.util.Arrays;
public class LeetCode727 {
	public String minWindow(String S, String T) {
        int m = S.length(), n = T.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            Arrays.fill(dp[i], m+1);
            dp[i][0] = 0;
        } 

        for (int i= 1; i <= m; i++){
	           for (int j = 1; j <= n; j++){
		              if (S.charAt(i-1) == T.charAt(j-1)){
			              dp[i][j] = dp[i-1][j-1] + 1;
		              } else {
			              dp[i][j] = dp[i-1][j] + 1;
		              }
	            }
        }	
        int res = m+1;
        int end = 0;
        for (int i = 1; i <= m; i++){
	              if (dp[i][n] < res){
		               end = i;
		               res = dp[i][n];
                 }
       }
       return res == m+1 ? "": S.substring(end-res, end);


   }
}
