/*author@ Qian Cai
 * Title@ Treasure Island
 * You have a map that marks the location of a treasure island. Some of the map area has 
 * jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other 
 * explorers trying to find the treasure. So you must figure out a shortest route to the 
 * treasure island.
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. 
 * You must start from the top-left corner of the map and can move one block up, down, left 
 * or right at a time. The treasure island is marked as X in a block of the matrix. X will 
 * not be at the top-left corner. Any block with dangerous rocks or reefs will be marked as D.
 *  You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe
 *  to sail in. The top-left corner is always safe. Output the minimum number of steps to get 
 *  to the treasure.
 * 
 * Solution: Typical BFS, explore from top-left position find the steps to reach X
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 */
import java.util.Queue;
import java.util.LinkedList;
public class TreasureIsland {
    public static int treasureIsland(char[][] island) {
    	int m = island.length;
    	int n = island[0].length;
    	int steps = 0;
    	Queue<int[]> queue = new LinkedList();
    	queue.add(new int[] {0, 0});
    	boolean[][] visited = new boolean[m][n];
    	visited[0][0] = true;
    	int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			int[] cur = queue.poll();
    			if (island[cur[0]][cur[1]] == 'X') return steps;
        		for (int d = 0; d < 4; d++) {
        			int x = cur[0] + dirs[d][0];
        			int y = cur[1] + dirs[d][1];
        			if (x >= 0 && x < m && y >= 0 && y < n && island[x][y] != 'D' && !visited[x][y]) {
        				queue.add(new int[] {x, y});
        				visited[x][y] = true;
        			}
        		}
    		}
    		steps++;
    	}
    	return -1;
    }
}
