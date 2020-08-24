/* author@ Qian Cai
 * Title@ Number of Islands II
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand 
 * operation which turns the water at position (row, col) into a land. Given a list of positions 
 * to operate, count the number of islands after each addLand operation. An island is surrounded 
 * by water and is formed by connecting adjacent lands horizontally or vertically. You may assume 
 * all four edges of the grid are all surrounded by water.
 * 
 */
import java.util.*;
public class Leetcode305 {
	//time: O(m*n + L)
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m*n);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Integer> ans = new ArrayList();
        for (int[] pos: positions){
            int r = pos[0], c= pos[1];
            
            int idx = r*n + c;
            dsu.set(idx);
            for (int d = 0; d < 4; d++){
                int x = r + dirs[d][0], y = c + dirs[d][1];
                if (x >= 0 && x < m && y >= 0 && y < n){
                    if (dsu.isLand(x*n + y)){
                        dsu.union(idx, x*n+y);
                    }
                }
            }
            ans.add(dsu.getCount());
        }
        return ans;
    }
    public class DSU{
        int count; //number of connected islands
        int[] parent;
        int[] size;
        public DSU(int n){
            count = 0;
            parent = new int[n];
            Arrays.fill(parent, -1);
            size = new int[n];
        }
        public int find(int x){
            while (x != parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        public void union(int x, int y){
            int xr = find(x), yr = find(y);
            if (xr != yr){
                if (size[xr] > size[yr]){
                    parent[yr] = xr;
                    size[xr] += size[yr];
                } else {
                    parent[xr] = yr;
                    size[yr] += size[xr];
                }
                count--;
            }
        }
        public boolean isLand(int x){
            return parent[x] >= 0;
        }
        public void set(int x){
            if (parent[x] == -1){
                parent[x] = x;
                count++;
            }
        }
        public int getCount(){
            return count;
        }
    }	
}
