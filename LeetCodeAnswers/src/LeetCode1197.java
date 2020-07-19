/* author@ Qian Cai
 * Title@ Minimum Knight Moves
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal 
direction, then one square in an orthogonal direction.
 * 
 */
import java.util.*;
public class LeetCode1197 {
	public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y); //according to symmetry
        if (x == 1 && y == 1) return 2;
        boolean[][] visited_s = new boolean[x+5][y+5];
       
        int step = 0;
        visited_s[0][0] = true;
       
        int[][] dirs = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, -2}, {1, 2}, {-1, 2}, {-1, -2}};
        Queue<int[]> q_start = new LinkedList();
        
        q_start.add(new int[]{0, 0});
       
        while (!q_start.isEmpty()){
            step++;
            int size_start = q_start.size();
            for (int i = 0; i < size_start; i++){
                int[] cur = q_start.poll();
                for (int d = 0; d < 8; d++){
                    int r = cur[0] + dirs[d][0], c = cur[1] + dirs[d][1];
                    if (r < 0 || r>= x+5 || c < 0 || c >= y+5) continue;
                    if (r == x && c == y) return step;
                    if (!visited_s[r][c]){
                        q_start.add(new int[]{r, c});
                        visited_s[r][c] = true;
                    }
                }
            }
            
            
        }
        return -1;
        
    }
}
