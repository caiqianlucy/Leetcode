/* author@ Qian Cai
 * Title@ Number of Submatrices That Sum to Target
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 * time: O(mn^2) space: O(m)
 */
import java.util.*;
public class LeetCode1074 {
	 public int numSubmatrixSumTarget(int[][] matrix, int target) {
	        int res = 0;
	        int m = matrix.length, n = matrix[0].length;
	        for (int c1 = 0; c1 < n; c1++){
	            int[] sum = new int[m];
	            for (int c2 = c1; c2 < n; c2++){
	                Map<Integer, Integer> map = new HashMap(); //key: c1 to c2 accumutive sum for [0, r], val: counts of sum
	                map.put(0, 1); //placeholder 
	                int total_sum = 0;
	                for (int r = 0; r < m; r++){ 
	                    sum[r] += matrix[r][c2];
	                    total_sum += sum[r];
	                    if (map.containsKey(total_sum - target)){
	                        res += map.get(total_sum-target);
	                    }
	                    map.put(total_sum, map.getOrDefault(total_sum, 0) + 1);
	                }
	            }
	        }
	        return res;
	    }
}
