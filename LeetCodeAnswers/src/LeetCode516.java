/*author@Qian Cai
 * Title@Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
 *
 * //Time, Space: O(n^2)
 */
public class LeetCode516 {
	public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1]; //dp[i][j] is longestPalindrome for s.substring(i, j);
        for (int len = 1; len <= n; len++){
            for(int i = 0; i+len-1 < n; i++){
                if (len == 1) dp[i][i+len] = 1;
                else dp[i][i+len] = (s.charAt(i) == s.charAt(len+i-1)) ?(dp[i+1][i+len-1]+2) : (Math.max(dp[i][i+len-1], dp[i+1][i+len]));
            }
        }
        return dp[0][n];
    }
}
