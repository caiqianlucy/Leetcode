/* author@ Qian Cai
 * Title@ Minimum Cost to Make at Least One Valid Path in a Grid
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some invalid signs on the cells of the grid which points outside the grid.

You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.
 * =================
 * Solution 1: Dijkstra's algorithm time (ElogV~mnlog(mn))
 * Solution 2: BFS + DFS time (mn)
 */
import java.util.*;
public class LeetCode1368 {
	public int minCost1(int[][] grid) {
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        int[][] cost = new int[m][n]; //cost to reach each position
        for (int i = 0; i < m; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        cost[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int c = cur[0], x = cur[1], y = cur[2];
            if (cost[x][y] != c) continue; //visited situation
            for (int i = 0; i < 4; i++){
                int nx = x+dir[i][0], ny = y + dir[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n){
                    int nc = c;
                    if (i != grid[x][y]-1) nc++;
                    if (cost[nx][ny] > nc){
                        cost[nx][ny] = nc;
                        pq.offer(new int[]{nc, nx, ny});                         
                    }
                }
            }
        }
        return cost[m-1][n-1];
        
    }
	int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost2(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] cost = new int[m][n]; //cost to reach each position
        for (int i = 0; i < m; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList();
        dfs(grid, 0, 0, cost, 0, queue);
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                for (int d = 0; d < 4; d++){
                    dfs(grid, x+dir[d][0], y+dir[d][1], cost, level, queue);
                }
            }
        }        
        return cost[m-1][n-1];       
    }
    public void dfs(int[][] grid, int x, int y, int[][] cost, int level, Queue<int[]> queue){
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || cost[x][y] != Integer.MAX_VALUE) return;
        cost[x][y] = level;
        //System.out.println("x is " + x + " y is " + y + " level is " + level);
        queue.offer(new int[]{x, y});
        int nextDir = grid[x][y]-1;
        dfs(grid, x+dir[nextDir][0], y+dir[nextDir][1], cost, level, queue);
    }
}
