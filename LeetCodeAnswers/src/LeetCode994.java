/*author@Qian Cai
 * Title@ Rotting Oranges
 * In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) 
to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 * Time: O(mn) space: O(mn)
 */
import java.util.Queue;
import java.util.LinkedList;
public class LeetCode994 {
	public int orangesRotting(int[][] grid) {
        int res = -1;
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 2) queue.add(i*n + j);
            }
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                rottenNeighbor(cur, grid, queue);
            }
            res++;
        }
        if (hasFresh(grid)) return -1;
        return res == -1? 0: res;      
    }
    public void rottenNeighbor(int cur, int[][] grid, Queue<Integer> queue){
        int m = grid.length;
        int n = grid[0].length;
        int x = cur/n;
        int y = cur % n;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int d = 0; d < 4; d++){
            int r = x + dirs[d][0], c = y + dirs[d][1];
            if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1){
                queue.add(r*n+c);
                grid[r][c] = 2;
            }
        }
    }
    public boolean hasFresh(int[][] grid){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1) return true;
            }
        }
        return false;
    }
}
