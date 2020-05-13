/* author@ Qian Cai
 * Title@ Number Of Distinct Islands
 * Given a non-empty 2D array grid of 0's and 1's, an island
 *  is a group of 1's (representing land) connected 4-directionally 
 *  (horizontal or vertical.) You may assume all four edges 
 *  of the grid are surrounded by water.

Count the number of distinct islands. An island is 
considered to be the same as another if and only if one 
island can be translated (and not rotated or reflected) 
to equal the other.
 * Time, Space complexity: O(mn)
 */
import java.util.*;
public class LeetCode694 {
	int[][] grid;
    boolean[][] seen;
    Set<List<Integer>> shapes;
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        seen = new boolean[m][n];
        shapes = new HashSet();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
               List<Integer> shape = new ArrayList(); //record the shape of each island
               dfs(i, j, 0, shape);
                if (!shape.isEmpty()){
                    shapes.add(shape);
                }
            }
        }
        return shapes.size();
    }
    public void dfs(int i, int j, int dir, List<Integer> shape){
        
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1 && !seen[i][j]){
            seen[i][j] = true;
            shape.add(dir);
            dfs(i+1, j, 1, shape);
            dfs(i-1, j, 2, shape);
            dfs(i, j+1, 3, shape);
            dfs(i, j-1, 4, shape);
            shape.add(0); //mark the end of the pos dfs
        }
    }
}
