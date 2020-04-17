/*author@ Qian Cai
 * Title@ Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression
 *  matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like 
. or *.
 * =====================================================================
 * Solution:
 * (1) recursion
 * n, m is length of s and p
 * Time, space complexity: O(n+m)2^(n+m/2)
 * (2) dp
 * Time, space complexity: O(nm)
 * 
 */
public class LeetCode10 {
	public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            return  first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
	public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;
        for (int i=s.length(); i>=0; i--){
            for (int j = p.length()-1; j>=0; j--){
                boolean match= (i < s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j) == '.'));
                if (j+1 < p.length() && p.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || match && dp[i+1][j];
                } else{
                    dp[i][j] = match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
