
public class LeetCode63 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else{
                    if (i == 0 && j == 0) dp[i][j] = 1;
                    else dp[i][j] = ((i == 0)? 0: dp[i-1][j]) + ((j == 0)?0:dp[i][j-1]);
                }
            }
        }
        return dp[m-1][n-1];
    }
}
