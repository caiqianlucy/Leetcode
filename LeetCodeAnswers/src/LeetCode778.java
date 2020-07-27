/* author@Qian Cai
 * Title@ Swim in Rising Water
 * Dijkstra Algorithm
 * time: O(n*nlogn)
 * space: O(n*n)
 * 
 */
import java.util.*;
public class LeetCode778 {
	 public int swimInWater(int[][] grid) {
	        int n = grid.length;
	        boolean[][] visited = new boolean[n][n];
	        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)-> (grid[a[0]][a[1]]-grid[b[0]][b[1]])); //weight is the time cost
	        int ans = 0;
	        visited[0][0] = true;
	        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	        pq.add(new int[]{0, 0});
	        while (!pq.isEmpty()){
	            int[] cur = pq.poll();
	            ans = Math.max(ans, grid[cur[0]][cur[1]]);
	            if (cur[0] == n-1 && cur[1] == n-1) return ans;
	            for (int d = 0; d < 4; d++){
	                int x = cur[0] + dirs[d][0];
	                int y = cur[1] + dirs[d][1];
	                if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]){
	                    visited[x][y] = true;
	                    pq.add(new int[]{x, y});
	                }
	            }
	        }
	        return -1;
	    }
}
