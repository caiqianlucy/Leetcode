/*author@ Qian Cai
 * Title@ Minimize Malware Spread
 * In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.

Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least one of those two nodes is infected by malware, both nodes will be infected by malware.  This spread of malware will continue until no more nodes can be infected in this manner.

Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread of malware stops.

We will remove one node from the initial list.  Return the node that if removed, would minimize M(initial).  If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.

Note that if a node was removed from the initial list of infected nodes, it may still be infected later as a result of the malware spread.
 * Time: O(n*n) Space: O(n), Solution1: Union Find 
 * Solution2: Color coding
 */
import java.util.Arrays;
public class LeetCode924 {
	 public int minMalwareSpread(int[][] graph, int[] initial) {
	        int n = graph.length;
	        DSU dsu = new DSU(n);
	        for (int i = 0; i < n; i++){
	            for (int j = 0; j < n; j++){
	                if (graph[i][j] == 1) dsu.union(i, j);
	            }
	        }
	        int ans = -1,  ansSize = -1;
	        int[] count = new int[n]; //find the node in the initial that is not connected with other nodes in initial
	        for (int node: initial){
	            count[dsu.parent[node]]++;
	        }
	        for (int node: initial){
	            int root = dsu.find(node);
	            if (count[root] == 1){
	                if (dsu.size[root] > ansSize || (dsu.size[root] == ansSize && node < ans)){
	                    ans = node;
	                    ansSize = dsu.size[root];
	                }
	            }
	        }
	        //if there is no unique node, return the node with smallest value
	        if (ans == -1){
	            ans = Integer.MAX_VALUE;
	            for (int node: initial) ans = Math.min(ans, node);
	        }
	        return ans;
	    }
	    public class DSU{
	        int[] parent;
	        int[] size;  //use size instead of rank because for getting the node to remove need size information
	        public DSU(int n){
	            parent = new int[n];
	            size = new int[n];
	            Arrays.fill(size, 1);
	            for (int i = 0; i < n; i++) parent[i] = i;
	        }
	        public int find(int x){
	            while (x != parent[x]){
	                parent[x] = parent[parent[x]]; //path compression
	                x = parent[x];
	            }
	            return x;
	        }
	        public void union(int x, int y){
	            int xP = find(x);
	            int yP = find(y);
	            if (xP == yP) return;
	            if (size[xP] < size[yP]){
	                parent[xP]=yP;
	                size[yP] += size[xP]; 
	            }  else {
	                parent[yP] = xP;
	                size[xP] += size[yP];
	            }
	        }  
	    }
	    class Solution {
	        public int minMalwareSpread(int[][] graph, int[] initial) {
	            //color each node
	            int n = graph.length;
	            int[] colors = new int[n];
	            Arrays.fill(colors, -1);
	            int color = 0;
	            for (int node = 0; node < n; node++){
	                if (colors[node] == -1){
	                    dfs(graph, colors, node, color++);
	                }
	            }
	            int[] colorCount = new int[color];
	            for (int c: colors){
	                colorCount[c]++;
	            }
	            int[] initialCount = new int[color];
	            for (int i: initial){
	                initialCount[colors[i]]++;
	            }
	            int ans = n+1;
	            //only unique color in the initial list if removed can prevent spread of malware
	            for (int node: initial){
	                int c = colors[node];
	                if (initialCount[c] == 1){
	                    if (ans == n+1){
	                        ans = node;
	                    } else if (colorCount[c] > colorCount[colors[ans]]){
	                        ans = node;
	                    } else if (colorCount[c] == colorCount[colors[ans]] && node < ans){
	                        ans = node;
	                    }
	                }
	            }
	            if (ans == n+1){
	                for (int node: initial){
	                    ans = Math.min(ans, node);
	                }
	            }
	            return ans;        
	        }
	        public void dfs(int[][] graph, int[] colors, int node, int color){
	            colors[node] = color;
	            for (int nei = 0; nei < graph.length; nei++){
	                if (graph[node][nei] == 1 && colors[nei] == -1){
	                    dfs(graph, colors, nei, color);
	                }
	            }
	        }
	    }
}
