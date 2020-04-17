/* author@ Qian Cai
 * Title@ Longest Increasing Path in a Matrix
 * Given an integer matrix, find the length of the longest increasing path.

 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around 
 * is not allowed).
 */
public class LeetCode329 {
	int m, n, res;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        res = 0;
        m = matrix.length; 
        n = matrix[0].length;
        int[][] memo = new int[m][n]; //memo of the depth
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                res = Math.max(res, dfs(i, j, memo, matrix));
            }
        }
        return res;
    }
    public int dfs(int i, int j, int[][] memo, int[][] matrix){        
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = 1;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int d = 0; d < 4; d++){
            int x = i+dirs[d][0], y = j+dirs[d][1];
            if (isValid(x, y) && matrix[x][y] > matrix[i][j]){
                memo[i][j] = Math.max(memo[i][j], 1+dfs(x, y, memo, matrix));
                //System.out.println("x: " + x + " y: " + y + " i: " + i + " j: " + j + " memo: " + memo[i][j]);
            }          
        }
        //System.out.println("i: " + i + " j: " + j + " memo: " + memo[i][j]);
        return memo[i][j];
    }
    public boolean isValid(int i, int j){
        return i >= 0 && i < m && j >= 0 && j < n;
    } 
}
