import java.util.Arrays;

//Time: O(mlogn) space(n), Krusckal's algorithm
public class LeetCode1135 {
	public int minimumCost(int N, int[][] connections) {
        DSU dsu = new DSU(N);
       Arrays.sort(connections, (int[] a, int[] b)->a[2]-b[2]);
       int edge = 0;
       int res = 0;
       for (int[] connection: connections){
           int city1 = connection[0], city2 = connection[1];
           if (dsu.find(city1) != dsu.find(city2)){
               edge++;
               res += connection[2];
               dsu.union(city1, city2);
           }
       }
       return edge == N-1 ? res: -1;
   }
   public class DSU{
       int[] parent;
       int[] rank;
       public DSU(int n){
           parent = new int[n+1];
           rank = new int[n+1];
           Arrays.fill(rank, 1);
           for (int i = 0; i <= n; i++) parent[i] = i;
       }
       public int find(int x){
           while (x != parent[x]){
               parent[x] = parent[parent[x]];
               x = parent[x];
           }
           return x;
       }
       public void union(int x, int y){
           int rootX = find(x);
           int rootY = find(y);
           if (rootX != rootY){
               if (rank[rootX] < rank[rootY]){
                   parent[rootX] = rootY;
               } else if (rank[rootX] > rank[rootY]){
                   parent[rootY] = rootX;
               } else {
                   parent[rootX] = rootY;
                   rank[rootX]++;
               }
           }
       }
   }
}
