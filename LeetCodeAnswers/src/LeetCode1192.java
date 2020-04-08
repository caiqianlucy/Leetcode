import java.util.*;
/*author@ Qian Cai
 * title@ Critical Connections in a Network
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.
 * Tarjan algorithm， Time,,space: O(V+E)
 */
public class LeetCode1192 {
	List<Integer>[] graph; //graph list of neighboring vertexes
    int[] ids; //the id assigned when it first discovered
    int[] low; //the lowest id reachable from that node
    int n; //total servers
    int time; //timestamp of each vertex
    List<List<Integer>> res;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.n = n;
        graph = new ArrayList[n];
        ids = new int[n];
        low = new int[n];
        time = 0;
        res = new ArrayList();
        Arrays.fill(ids, -1);
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList();
        }
        for (List<Integer> connection: connections){
            int from = connection.get(0), to = connection.get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        for (int i = 0; i < n; i++){
            if (ids[i] == -1) dfs(i, i); //dfs for all servers not visited
        }
        return res;
    }
    public void dfs(int from, int prev){
        low[from] = ids[from] = time++;
        for (int to: graph[from]){
            if (to == prev) continue; //do not go back to parent server
            if (ids[to] == -1){
                dfs(to, from);
                low[from] = Math.min(low[from], low[to]);
                if (low[to] > ids[from]){
                    res.add(Arrays.asList(from, to));
                }
            }
            low[from]= Math.min(low[from], ids[to]);
        }
    }
}
