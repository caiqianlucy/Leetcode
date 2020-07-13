/* author@ Qian Cai
 * Title@ Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.
Constraints:

m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.
 * ============================================================
 * use an Integer to record the state of the mat
    bfs to visit the mat
    time: O(mn*2^(mn)) space: O(2^(mn))
 */
import java.util.*;
public class LeetCode1284 {
	public int minFlips(int[][] mat) {
        int start = 0, m = mat.length, n = mat[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                start |= mat[i][j] << (i*n + j);
            }
        }
        queue.add(start);
        Set<Integer> seen = new HashSet();
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s < size; s++){
                int cur = queue.poll();
                if (cur == 0) return step;
                for (int i = 0; i < m; i++){
                    for (int j = 0; j < n; j++){
                        int next = cur;
                        next ^= 1<<(i*n + j);       
                        for (int d = 0; d < 4; d++){
                            int r = i + dirs[d][0], c = j + dirs[d][1];
                            if (r >= 0 && r < m && c >= 0 && c < n){
                                next ^= 1 << (r*n + c);
                            }
                            
                        }
                        if (!seen.contains(next)){
                                queue.add(next);
                                seen.add(next);
                        }
                    }
                }
            }
            step++;
        } 
        return -1;
        
    }
}
