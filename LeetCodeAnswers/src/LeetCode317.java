/* author@ Qian Cai
 * Title@ Shortest Distance from All Buildings
 * 
 * 
 */
import java.util.*;
public class LeetCode317 {
	int[][] grid;
    int m, n;
    public int shortestDistance(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] distance = new int[m][n]; //total distance to all buildings
        int[][] count = new int[m][n];  //reachable buildings
        int c = 0; //total building count;
        this.grid = grid;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    bfs(distance, count, i, j);
                    c++;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0){
                    if (count[i][j] == c){
                        res = Math.min(res, distance[i][j]);
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public void bfs(int[][] distance, int[][] count, int i, int j){
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, { 0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList();
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        queue.add(new int[]{i, j});
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int  k = 0; k < size; k++){
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++){
                    int x = cur[0] + dirs[d][0], y = cur[1] + dirs[d][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0){
                        distance[x][y] += level;
                        count[x][y]++;
                        queue.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
        }
    }
}
