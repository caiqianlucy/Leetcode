/*author@ Qian Cai
 * Title@ Treasure Island
 * You have a map that marks the locations of treasure islands.
 *  Some of the map area has jagged rocks and dangerous reefs.
 *   Other areas are safe to sail in. There are other explorers trying to find the treasure. 
 *   So you must figure out a shortest route to one of the treasure islands.

 * Assume the map area is a two dimensional grid, represented by a matrix of characters. 
 * You must start from one of the starting point (marked as S) of the map and can move one
 * block up, down, left or right at a time. The treasure island is marked as X. Any block 
 * with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. 
 * You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number 
 * of steps to get to any of the treasure islands.
 * 
 * Solution: Typical BFS, explore from top-left position find the steps to reach X
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * */
import java.util.Queue;
import java.util.LinkedList;
public class TreasureIslandII {
	public static int treasureIslandII(char[][] island) {
    	int m = island.length;
    	int n = island[0].length;
    	int steps = 0;
    	boolean[][] visited = new boolean[m][n];
    	Queue<int[]> queue = new LinkedList();
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (island[i][j] == 'S') {
    				queue.add(new int[] {i, j});
    				visited[i][j] = true;
    			}
    		}
    	}
    	
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
	public static void main(String[] args) {
       
        char[][] grid1=new char[][]{{'S', 'O', 'O', 'S', 'S'},
                                    {'D', 'O', 'D', 'O', 'D'},
                                    {'O', 'O', 'O', 'O', 'X'},
                                    {'X', 'D', 'D', 'O', 'O'},
                                    {'X', 'D', 'D', 'D', 'O'}};
        
        char[][] grid2=new char[][]{{'S', 'O', 'O', 'S', 'S'},
                                    {'D', 'O', 'D', 'O', 'O'},
                                    {'O', 'O', 'O', 'O', 'X'},
                                    {'X', 'D', 'D', 'O', 'O'},
                                    {'X', 'D', 'D', 'D', 'O'}};        
        int tc1 = treasureIslandII(grid1);
        int tc2 = treasureIslandII(grid2);
        if(tc1==3 && tc2==2) {
            System.out.println("All Test Case Passes!");
        } else {
            System.out.println("There are test failures!");
        }
    }
    
}
