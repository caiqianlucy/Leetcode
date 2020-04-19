/* author@ Qian Cai
 * Title@  Build Array Where You Can Find The Maximum Exactly K Comparisons
 * Time : O(n*m*k*m) space: O(n*m*k)
 */
public class LeetCode1420 {
	int MOD = (int)Math.pow(10, 9) + 7;
    public int numOfArrays(int n, int m, int k) {
        Integer[][][] dp = new Integer[m+1][n+1][k+1];
        return dfs(n, m, k, 0, 0, 0, dp);
    }
    public int dfs(int n, int m, int k, int curMax, int i, int searchCost, Integer[][][] dp){
        if (i == n){
            if (searchCost == k) return 1;
            return 0;
        }
        if (dp[curMax][i][searchCost] != null) return dp[curMax][i][searchCost];
        int ans = 0;
        for (int num = 1; num <= m; num++){
            int newCost = searchCost;
            int newMax = curMax;
            if (num > curMax){
                newCost++;
                newMax = num;
            }
            if (newCost > k) break;
            ans += dfs(n, m, k, newMax, i+1, newCost, dp);
            ans %= MOD;
        }
        return dp[curMax][i][searchCost] = ans;
    }
}
