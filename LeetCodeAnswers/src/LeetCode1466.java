/* author@ Qian Cai
 * Title@ Reorder Routes to Make All Paths Lead to the City Zero
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach the city 0 after reorder.

 
 * 
 */
import java.util.*;

public class LeetCode1466 {
	public int minReorder(int n, int[][] connections) {
        boolean[] visited = new boolean[n];
        Map<Integer, List<Integer>> adj = new HashMap();
        Map<Integer, Set<Integer>> dir = new HashMap();
        for (int[] connection: connections){
            int from = connection[0], to = connection[1];
            if (!adj.containsKey(from)) adj.put(from, new ArrayList());
            adj.get(from).add(to);
            if (!adj.containsKey(to)) adj.put(to, new ArrayList());
            adj.get(to).add(from);
            if (!dir.containsKey(to)) dir.put(to, new HashSet());
            dir.get(to).add(from);
        }
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        visited[0] = true;
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                for (int next: adj.get(cur)){
                    if (!visited[next]){
                        visited[next] = true;
                        queue.add(next);
                        if (!dir.containsKey(cur) || !dir.get(cur).contains(next)) res++;
                    }
                }
            }
        }
        return res;
    }
}
