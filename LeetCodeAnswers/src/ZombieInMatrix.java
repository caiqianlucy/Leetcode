/*author@Qian Cai
 * Title@ Zombie in Matrix
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. 
 * Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. 
 * Find out how many hours does it take to infect all humans?
 * Time: O(mn) Space: O(mn), BFS
 */
import java.util.Queue;
import java.util.LinkedList;
public class ZombieInMatrix {
    public static int minDays(int[][] grid) {
    	int m = grid.length;
    	int n = grid[0].length;
    	Queue<int[]> zombies = new LinkedList();
    	int cnt = 0; //count of zombie number
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == 1) {
    				zombies.offer(new int[] {i, j});
    				cnt++;
    			}
    		}
    	}
    	int res = 0;
    	int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    	while (!zombies.isEmpty()) {
    		if (cnt == m*n) return res; // all cells are zombie
    		int size = zombies.size();
    		for (int i = 0; i < size; i++) {
    			int[] cur = zombies.poll();
    			for (int d = 0; d < 4; d++) {
    				int x = cur[0] + dirs[d][0];
    				int y = cur[1] + dirs[d][1];
    				if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
    					zombies.offer(new int[] {x, y});
    					grid[x][y] = 1;
    					cnt++;
    				}
    			}
    		}
    		res++;
    	}
    	return -1; 
    }
    public static void main(String[] args) {
    	int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
    	System.out.println(minDays(grid));
    }
}
