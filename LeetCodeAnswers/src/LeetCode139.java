/* author@ Qian Cai
 * Title@ Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 */
import java.util.*;
public class LeetCode139 {
	public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet(wordDict);
     boolean[] dp = new boolean[s.length()+1];
     dp[0] = true;
     for (int i = 1; i < s.length()+1; i++){
            for (int j = 0; j < i; j++){
                   if (dp[j] && dict.contains(s.substring(j,i))){
                       dp[i] = true;
                       break;
                   }
            }
     }
     return dp[s.length()];
    }
}
