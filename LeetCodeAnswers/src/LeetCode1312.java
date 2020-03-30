/*author@Qian Cai
 * Title@Minimum Insertion Steps to Make a String Palindrome
 * Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.
 * 
 * Conversion of LeetCode516
 * //Time and space: O(n^2)
 */
public class LeetCode1312 {
	public int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }
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
