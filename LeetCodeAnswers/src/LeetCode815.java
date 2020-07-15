/* author@ Qian Cai
 * Title@ Bus Routes
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. 
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the
 *  sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses
 only, what is the least number of buses we must take to reach our destination? Return -1 if it is not
  possible.
 * 1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5.
0 <= routes[i][j] < 10 ^ 6.
 */
import java.util.*;
public class LeetCode815 {
	/*BFS, time: O(m*n^2 + nmlogm) n is the routes.length, m is routes[0].length
    sorting each bus routes takes nmlogm
    build graph takes m*n^2
    find start end takes nlogm
    bfs takes n^2
    */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0; //handle corner case
        int n = routes.length;
         Map<Integer, List<Integer>> graph = new HashMap(); //build graph from one bus to the other bus
        for (int i = 0; i < n; i++) {
            Arrays.sort(routes[i]); //alternative sacrifice space use set to check intersect, extra time once, extra space multiple times because need to compare with each one
            graph.put(i, new ArrayList());
        }
        //build graph
        for (int i = 0; i < n-1; i++){
            for (int j = i+1; j < n; j++){
                if(intersect(routes[i], routes[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        //find start and end bus         
        Set<Integer> visited = new HashSet();
        Queue<Integer> queue = new LinkedList();
        Set<Integer> target = new HashSet();
        for (int i = 0; i < n; i++){
            if (Arrays.binarySearch(routes[i], S) >= 0){
                queue.add(i);
                visited.add(i);
            }
            if (Arrays.binarySearch(routes[i], T) >= 0){
                target.add(i);
            }
        }
       
        int step = 1; //initial take one bus that contains start
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int busId = queue.poll();
                if (target.contains(busId)) return step;
                for (int nei: graph.get(busId)){
                    if (!visited.contains(nei)){
                        visited.add(nei);
                        queue.add(nei);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    public boolean intersect(int[] A, int[] B){
        int i = 0, j = 0;
        while (i < A.length && j < B.length){
            if (A[i] == B[j]) return true;
            else if (A[i] > B[j]) j++;
            else i++;
        }
        return false;
    }
}
