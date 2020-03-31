import java.util.*;

/*author@ Qian Cai
 * Title@ Tree Diameter
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 * Time: O(n) Space O(n^2), similar to LeetCode543
 */
public class LeetCode1245 {
	int res;
    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new List[n+1];
        for (int i = 0; i <= n; i++) graph[i] = new LinkedList();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        res = 0;
        helper(graph, 0, -1);
        return res;
    }
    //helper function to traverse the tree, update res, and return the depth
    public int helper(List<Integer>[] graph, int cur, int parent){
        
        int largest = 0, second = 0;
        for (int next: graph[cur]){
            //do not go back
            if (next != parent){
                int nextDepth = helper(graph, next, cur);
                if ( nextDepth > largest){
                    second = largest;
                    largest = nextDepth;  
                } else if (nextDepth > second){
                    second = nextDepth;
                }
            }
        }
        res = Math.max(res, largest+second);
        return largest + 1;
    }
}
