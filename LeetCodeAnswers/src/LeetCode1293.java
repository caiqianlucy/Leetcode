/* author@ Qian Cai
 * Title@ Shortest Path in a Grid with obstacles Elimination
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower 
right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not 
possible to find such walk return -1.
 * 
 */
import java.util.*;
public class LeetCode1293 {
	public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; //minimum #obstacle need to remove at grid[i][j]
        //initialize dp
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][0] = 0;
        Queue<int[]> queue = new LinkedList(); //bfs
        queue.add(new int[]{0, 0});
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] pos = queue.poll();
                int x = pos[0],  y = pos[1];
                if (x == m-1 && y == n-1) return step;
                for (int d = 0; d < 4; d++){
                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                    
                    if (isValid(nx, ny, m, n)){
                        int obs = dp[x][y] + grid[nx][ny];
                        if (dp[nx][ny] > obs && obs <= k){
                            queue.add(new int[]{nx, ny});
                            dp[nx][ny] = obs;
                        }
                    }
                }
               
            }
             step++;
        }
        return -1;
    }
    public boolean isValid(int x, int y, int m, int n){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
