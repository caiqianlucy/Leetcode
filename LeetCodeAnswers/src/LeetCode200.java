/* author@ Qian Cai
 * Title@ Number Of Islands
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands 
 * horizontally or vertically. You may assume all four edges of the grid are all 
 * surrounded by water.
 * Time, space: O(mn) dfs
 */
public class LeetCode200 {
	int m, n;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0;  j < n; j++){
                if (grid[i][j] == '1' && !visited[i][j]){
                    dfs(i, j, grid, visited);
                    res++;
                }
            }
        }
        return res;
    }
    public void dfs(int i, int j, char[][] grid, boolean[][] visited){
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' ||visited[i][j]) return;
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        visited[i][j] = true;
        for (int d = 0; d < 4; d++){
            int nr = i+dirs[d][0];
            int nc = j+dirs[d][1];
            dfs(nr, nc, grid, visited);
        }
    }
}
