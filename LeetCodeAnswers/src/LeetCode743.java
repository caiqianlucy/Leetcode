/* author@ Qian Cai
 * Title@ Network Delay Time
 * There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.


 * time: O(ElogE) space: O(E)
 */
import java.util.*;
public class LeetCode743 {
	public int networkDelayTime(int[][] times, int N, int K) {
	       List<List<int[]>> graph = new ArrayList();
	        for (int i = 0; i <= N; i++){
	            graph.add(new ArrayList());
	        }
	        for (int[] time: times){
	            int from = time[0], to = time[1], cost = time[2];
	            graph.get(time[0]).add(new int[]{to, cost});
	        }
	        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(a[1]-b[1]));
	        int[] delayTime = new int[N+1];
	        Arrays.fill(delayTime, Integer.MAX_VALUE);
	        pq.add(new int[]{K, 0});
	        while (!pq.isEmpty()){
	            int[] cur = pq.poll();
	            if (delayTime[cur[0]] == Integer.MAX_VALUE){
	                delayTime[cur[0]] = cur[1];
	                for (int[] nei: graph.get(cur[0])){
	                    if (delayTime[nei[0]] == Integer.MAX_VALUE){
	                        pq.add(new int[]{nei[0], nei[1]+cur[1]});
	                    }
	                }
	            }
	        }
	        int res = 0;
	        for (int i = 1; i <= N; i++){
	            if (delayTime[i] == Integer.MAX_VALUE) return -1;
	            res = Math.max(res, delayTime[i]);
	        }
	        return res;
	    }
}
