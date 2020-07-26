/* author@ Qian Cai
 * Title@ String Compression II
 * Run-length encoding is a string compression method that works by replacing consecutive 
 * identical characters (repeated 2 or more times) with the concatenation of the character
 *  and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that
 the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k 
characters.
 * Time: O(k*n^2)
 */
import java.util.*;
public class LeetCode1531 {
	int[][][][] memo;
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        memo = new int[26][n+1][n+1][k+1];
        for (int i = 0; i < 26; i++){
            for (int j = 0; j <= n; j++){
                for (int l = 0; l <= n; l++){
                    Arrays.fill(memo[i][j][l], -1);
                }
            }
        }
        return helper(s, s.charAt(0)-'a', 0, 0, k);
    }
    public int helper(String s, int p_char, int p_cnt, int i, int k){
        if (i == s.length()) return len(p_cnt);
        if (memo[p_char][p_cnt][i][k] != -1) return memo[p_char][p_cnt][i][k];
        int ans = 0;
        if (s.charAt(i) -'a' == p_char){
            ans = helper(s, p_char, p_cnt+1, i+1, k);
            //delete             
        } else {
            //not equal to prev char
            ans = helper(s, s.charAt(i)-'a', 1, i+1, k) + len(p_cnt);
        }
        if (k > 0){
                ans = Math.min(ans, helper(s, p_char, p_cnt, i+1, k-1));
        }
        memo[p_char][p_cnt][i][k] = ans;
        return ans;
        
        
    }
    public int len(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        return n <= 9 ? 2 : n <= 99 ? 3: 4;
    }
}
