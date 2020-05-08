/* author@ Qian Cai
 * Title@ Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns), 
 * return all elements of the matrix in spiral order.
 * Time: O(n)
 * Space: O(n) n is number of elements in the 2d array
 */
import java.util.*;
public class LeetCode54 {
	public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new ArrayList();
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[][] seen = new boolean[m][n];
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> res = new ArrayList();
        int size = 0;
        int x = 0;
        int y = 0;
        int d = 0;
        while (size < n*m){
            size++;
            seen[x][y] = true;
            //System.out.println(matrix[x][y]);
            res.add(matrix[x][y]);
            while (size < n*m && !isValid(x+dirs[d][0], y+dirs[d][1], m, n, seen)){
                d = (d+1)%4;
            }   
            x = x+dirs[d][0];
            y = y+dirs[d][1];
        }
        return res;
    }
    public boolean isValid(int x, int y, int m, int n, boolean[][] seen){
        return x >= 0 && x < m && y >= 0 && y < n && !seen[x][y];
    }
}
