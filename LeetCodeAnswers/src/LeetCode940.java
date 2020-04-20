 /* author@ Qian Cai
  * Title@ Distinct Subsequence II
  * Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.
 * Time complexity: O(n) Space complexity: O(n)
 */
import java.util.Arrays;
public class LeetCode940 {
	public int distinctSubseqII(String S) {
        int MOD = (int)(Math.pow(10, 9)) + 7;
        int n = S.length();
        long[] dp = new long[n+1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++){
            int x = S.charAt(i)-'a';
            dp[i+1] = dp[i] * 2;
            if (last[x] >= 0){
                dp[i+1] -= dp[last[x]]; //the sequence that counted with last idx of x will be double counted
            }
            dp[i+1] %= MOD;
            //System.out.println(dp[i+1]);
            last[x] = i;
        }
        dp[n]--; //remove the empty "" string
        if (dp[n] < 0) dp[n] += MOD; //after modulo, there might be negative value
        return (int)dp[n];
    }
}
