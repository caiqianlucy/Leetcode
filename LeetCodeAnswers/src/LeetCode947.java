/* author@ Qian Cai
 * Title@ Most Stones Removed with Same Row or Column
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?
 * 
 */
import java.util.*;
public class LeetCode947 {
	//Union Find, time, space: O(n)
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DSU dsu = new DSU(n);
        Map<Integer, List<Integer>> rows = new HashMap();
        Map<Integer, List<Integer>> cols = new HashMap();
        for (int i = 0; i < n; i++){
            int r = stones[i][0], c = stones[i][1];
            if (!rows.containsKey(r)) rows.put(r, new ArrayList());
            if (!cols.containsKey(c)) cols.put(c, new ArrayList());
            rows.get(r).add(i);
            cols.get(c).add(i);
        }
        for (int key: rows.keySet()){
            List<Integer> sameRows = rows.get(key);
            if (sameRows.size() > 1){
                for (int i = 1; i < sameRows.size(); i++){
                    dsu.union(sameRows.get(i-1), sameRows.get(i));
                }
            }
        }
        for (int key: cols.keySet()){
            List<Integer> sameCols = cols.get(key);
            if (sameCols.size() > 1){
                for (int i = 1; i < sameCols.size(); i++){
                    dsu.union(sameCols.get(i-1), sameCols.get(i));
                }
            }
        }
        return n-dsu.count;
    }
    public class DSU{
        int[] parent;
        int[] size;
        int count; //how many connected component are there
        public DSU(int n){
            parent = new int[n];
            for (int i = 0; i < n; i++){
                parent[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
            count = n;
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
                count--;
                if (size[xr] < size[yr]){
                    parent[xr] = yr;
                    size[yr] += size[xr];     
                } else{
                    parent[yr] = xr;
                    size[xr] += size[yr];
                }
            }
        }
    }
}
