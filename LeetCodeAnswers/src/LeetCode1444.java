/* author@Qian Cai
 * Title@ Number of Ways of Cutting a Pizza
 * dynamic programming
 * time: O((m+n)*m*n*k
 * space: O(m*n*k)
 * 
 */
public class LeetCode1444 {
	int MOD = (int)Math.pow(10, 9) + 7;
    int m, n;
    int[][] apples;
    Integer[][][] dp;
    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        apples = new int[m+1][n+1]; //apples[i][j] represents accumutive apples from topleft at[i][j] to bottomright[m-1][n-1] rectangle region
        dp = new Integer[m][n][k+1];
        for (int i = m-1; i >= 0; i--){
            for (int j = n-1; j >= 0; j--){
                apples[i][j] = apples[i+1][j] + apples[i][j+1] - apples[i+1][j+1];
                apples[i][j] += (pizza[i].charAt(j) == 'A' ? 1: 0);
            }
        }
        
        return dfs(0, 0, k);
    }
    public int dfs(int i, int j, int k){
        if (apples[i][j] == 0) return 0;
        if (k == 1) return 1;
        if (dp[i][j][k] != null) return dp[i][j][k];
        long ans = 0;
        //vertical cut
        for (int r = i+1; r < m; r++){
            if (apples[i][j] > apples[r][j]){
                ans += dfs(r, j, k-1);
                ans %= MOD;
            }
        }
        //horizontal cut
        for (int c = j+1; c < n; c++){
            if (apples[i][j] > apples[i][c]){
                ans += dfs(i, c, k-1);
                ans %= MOD;
            }
        }
        dp[i][j][k] = (int)ans;
        return dp[i][j][k];

    }
}
