/* author@ Qian Cai
 * Title@ Number of Closed Islands
 * 
 */
public class LeetCode1254 {
	class Solution {
	    public int closedIsland(int[][] grid) {
	        int res = 0;
	        int m = grid.length;
	        int n = grid[0].length;
	        //change all the land by connected to water as 1
	        for (int i = 0; i < n; i++){
	            if (grid[0][i] == 0) dfs(grid, 0, i);
	            if (grid[m-1][i] == 0) dfs(grid, m-1, i);
	        }
	        for (int i = 1; i < m-1; i++){
	            if (grid[i][0] == 0) dfs(grid, i, 0);
	            if (grid[i][n-1] == 0) dfs(grid, i, n-1);
	        }
	        for (int i = 1; i < m-1; i++){
	            for (int j = 1; j < n-1; j++){
	                if (grid[i][j] == 0){
	                    res++;
	                    dfs(grid, i, j);
	                }
	            }
	        }
	        return res;
	        
	        
	    }
	    public void dfs(int[][] grid, int i, int j){
	        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) return;
	        grid[i][j] = 1;
	        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	        for (int d = 0; d < 4; d++){
	            dfs(grid, i+dirs[d][0], j+dirs[d][1]);
	        }
	    }
	}
}
