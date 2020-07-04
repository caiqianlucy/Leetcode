/* author@ Qian Cai
 * Title@ Trapping Rain Water II
 * Given an m x n matrix of positive integers representing the height of each 
 * unit cell in a 2D elevation map, compute the volume of water it is able to
 *  trap after raining.
 * Solution: 
 * PriorityQueue
 * Time complexity O(mnlog(m+n))
 * Space complexity O(m+n)
 */
import java.util.*;
public class LeetCode407 {
	public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) return 0;
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(a[0]-b[0]));
        for (int i = 0; i < m; i++){
            pq.add(new int[]{heightMap[i][0], i, 0});
            visited[i][0] = true;
            if ( n > 1){
                pq.add(new int[]{heightMap[i][n-1], i, n-1});
                visited[i][n-1] = true;
            }
        }
        if (n > 2){
            for (int j = 1; j < n-1; j++){
                pq.add(new int[]{heightMap[0][j], 0, j});
                visited[0][j] = true;
                if (m > 1){
                    pq.add(new int[]{heightMap[m-1][j], m-1, j});
                    visited[m-1][j] = true;
               }
            }
            
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int boundary = cur[0], i = cur[1], j = cur[2];
            res += boundary - heightMap[i][j];
            for (int d = 0; d < 4; d++){
                int x = i + dirs[d][0], y  = j + dirs[d][1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                    visited[x][y] = true;
                    pq.add(new int[]{Math.max(boundary, heightMap[x][y]), x, y});
                }
            }
        }
        return res;
    }
}
