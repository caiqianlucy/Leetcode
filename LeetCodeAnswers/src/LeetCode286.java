/*author@Qian Cai
 * Title@ Walls and Gates
 * You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent
 INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to 
reach a gate, it should be filled with INF.
 * Time: O(mn), Each room is visited at most once, which makes the time complexity as O(mn)
 * Space: O(mn)
 */
import java.util.*;
public class LeetCode286 {
	int m, n;
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        m = rooms.length;
        n = rooms[0].length;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (rooms[i][j] == 0) queue.add(i*n+j);
            }
        }
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                checkNeighbor(cur, rooms, queue, level);
            }
        }
    }
    public void checkNeighbor(int cur, int[][] rooms, Queue<Integer> queue, int level){
        int x = cur/n;
        int y = cur%n;
        int[][] dirs = new int[][]{{-1, 0}, {0, 1},{0, -1}, {1, 0}};
        for (int d = 0; d < 4; d++){
            int r = x + dirs[d][0], c = y + dirs[d][1];
            if (r >= 0 && r < m && c >= 0 && c < n && rooms[r][c] == Integer.MAX_VALUE){
                rooms[r][c] = level;
                queue.add(r*n+c);
            }
        }
        
    }
}
