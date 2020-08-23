/* author@ Qian Cai
 * Title@ Profitable Schemes
 * There is a group of G members, and a list of various crimes they could commit.

The ith crime generates a profit[i] and requires group[i] members to participate in it.

If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 */
public class LeetCode879 {
	//time: O(P*G*N) space: O(P*G)
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int MOD = 1_000_000_007;
        int N = group.length;
        long[][][] dp = new long[2][P+1][G+1]; //rolling index, exceeding P can be considered as P
        dp[0][0][0] = 1;
        for (int i = 0; i < N; i++){
            int p = profit[i], g = group[i];
            for (int r = 0; r <= P; r++){
                for (int c = 0; c <= G; c++){
                    dp[(i+1)%2][r][c] = dp[i%2][r][c]; //copy last schemes
                }
            }
            for (int r = 0; r <= P; r++){
                int p2 = Math.min(p+r, P);
                for (int c = 0; c <= G-g; c++){
                    dp[(i+1)%2][p2][c+g] += dp[i%2][r][c];
                    dp[(i+1)%2][p2][c+g] %= MOD;
                }
            }
        }
        //sum all schemes with P
        long ans = 0;
        for (long x: dp[N%2][P]){
            ans += x;
            ans %= MOD;
        }
        return (int)ans;
    }
}
