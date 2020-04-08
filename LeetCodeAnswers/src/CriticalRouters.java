/*author@ Qian Cai
 *Title@ Critical Routers
 * You are given an undirected connected graph. An articulation point (or cut vertex) 
 * is defined as a vertex which, when removed along with associated edges, makes the 
 * graph disconnected (or more precisely, increases the number of connected components
 *  in the graph). The task is to find all articulation points in the given graph.

Input:
The input to the function/method consists of three arguments:

numNodes, an integer representing the number of nodes in the graph.
numEdges, an integer representing the number of edges in the graph.
edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
Output:
Return a list of integers representing the critical nodes.
*
* Tarjan Algorithm, Time : O(V+E), Space: O(V+E)
 */
import java.util.*;
public class CriticalRouters {
   
	static int time = 0;
	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		time = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(int i=0;i<numRouters;i++) {
			map.put(i, new HashSet<>());
		}
		for(int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		Set<Integer> set = new HashSet<>();
		int[] low = new int[numRouters];
		int[] ids = new int[numRouters];
		
		Arrays.fill(ids, -1);

		for(int i=0;i<numRouters;i++) {
			if(ids[i] == -1)
				dfs(map, low, ids, -1, i, set);
		}
		return new ArrayList<>(set);
	}



	private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int parent, int cur, Set<Integer> res) {
		int children = 0; 
		ids[cur] = low[cur]= ++time;
		for(int nei : map.get(cur)) {
			if(ids[nei] == -1) {
				children++;
				
				dfs(map, low, ids, cur, nei, res);
				low[cur] = Math.min(low[cur], low[nei]);
				if((parent == -1 && children > 1) || (parent != -1 && low[nei] >= ids[cur]))
					res.add(cur);
			}
			else if(nei != parent)
				low[cur] = Math.min(low[cur], ids[nei]);
		}
	}
	public static void main(String[] args) {
		int numRouters1 = 7;
		int numLinks1 = 7;
		int[][] links1 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}
}
