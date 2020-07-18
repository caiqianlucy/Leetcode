/* author@ Qian Cai
 * Title@ Cherry Pickup
 * time: O(n^3) space: O(n^2)
 * 
 */
import java.util.*;
public class LeetCode741 {
	public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] prev = new int[n][n];
        for (int[] row: prev){               
                Arrays.fill(row, Integer.MIN_VALUE);
        } 
        prev[0][0] = grid[0][0];
        for (int step = 1; step <= 2*n - 2; step++){
            int[][] cur = new int[n][n];
             for (int[] row: cur){                     
                Arrays.fill(row, Integer.MIN_VALUE);                      
             }      
            for (int c1 = Math.max(0, step-(n-1)); c1 <= Math.min(n-1, step); c1++){
                for (int c2 = Math.max(0, step-(n-1)); c2 <= Math.min(n-1, step); c2++){
                    if (grid[step-c1][c1] == -1 || grid[step-c2][c2] == -1) continue;
                    int res = grid[step-c1][c1];
                    if (c1 != c2) res += grid[step-c2][c2];
                    
                    for (int d1 = -1; d1 <= 0; d1++){
                        for (int d2 = -1; d2 <= 0; d2++){
                            if (c1+d1 < 0 ||c2+d2 < 0) continue;
                            cur[c1][c2] = Math.max(cur[c1][c2], prev[c1+d1][c2+d2] + res);
                        }
                    }
                }
            }
            prev = cur;
        }   
        return Math.max(0, prev[n-1][n-1]); 
	}
}
