/* author@ Qian Cai
 * Title@ Bricks Falling When Hit
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.
Reverse union-find
time: O(mnk) m: row of grid, n: col of grid, k: len of hits
 */
import java.util.*;
public class LeetCode803 {
	public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] arr = new int[m][n]; //reverse the brick in hits first
        for (int i = 0; i < m; i++) arr[i] = grid[i].clone();
        for (int[] hit: hits){
            arr[hit[0]][hit[1]] = 0;
        }
        DSU dsu = new DSU(m*n + 1);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (arr[i][j] == 1){
                    if (i == 0) dsu.union(j, m*n);
                    if (i > 0 && arr[i-1][j] == 1) dsu.union(i*n + j, (i-1)*n + j);
                    if (j > 0 && arr[i][j-1] == 1) dsu.union(i*n + j, i*n + j-1);
                }
            }
        }
        
        
        int t = hits.length;
        int[] ans = new int[t--];
        while (t >= 0){
            int r = hits[t][0], c = hits[t][1];
            if (grid[r][c] == 0){
                t--;
            } else {
                int pre = dsu.getTop(); //bricks connected to top after removing all hits
                //System.out.println(pre);
            
                for (int d = 0; d < 4; d++){
                    int x = r + dirs[d][0], y = c + dirs[d][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && arr[x][y] == 1){
                        dsu.union(x*n+y, r*n + c);
                     }
                }
                if (r == 0) dsu.union(r*n + c, m*n);
                arr[r][c] = 1;
                ans[t--] = Math.max(0, dsu.getTop()- pre -1);
            }      
         }
        return ans;
        
    }
    class DSU{
        int[] p;
        int[] size;
        public DSU(int n){
            p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
            size = new int[n];
            Arrays.fill(size, 1);
        }
        public int find(int x){
            while (p[x] != x){
                p[x] = p[p[x]];
                x = p[x];
            }
            return x;
        }
        public void union(int x, int y){
            int xr = find(x), yr = find(y);
            if (xr != yr){
                if (size[xr] >= size[yr]){
                    p[yr] = xr;
                    size[xr] += size[yr];
                } else {
                    p[xr] = yr;
                    size[yr] += size[xr];
                }
            }
        }
        public int getTop(){
            return size[find(size.length-1)] - 1; //bricks connected to top
        }
    }
}
