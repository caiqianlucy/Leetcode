/* author@ Qian Cai
 * Title@ Cheapest Flights Within K Stops
 * There are n cities connected by m flights. Each flight starts from city u and arrives at 
 * v with a price w.

Now given all the cities and flights, together with starting city src and the destination
 dst, your task is to find the cheapest price from src to dst with up to k stops. If there 
 is no such route, output -1.
 * Time/space complexcity: O(E+V)
 */

import java.util.PriorityQueue;
public class LeetCode787 {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
	for(int[] edge: flights){
		if(!prices.containsKey(edge[0])) prices.put(edge[0], new HashMap<>());
		prices.get(edge[0]).put(edge[1], edge[2]);
	}

	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
		@Override
		public int compare(int[] a, int[] b){
			return a[0]-b[0];
		}
	});
	pq.offer(new int[]{0, src, K});
	while(!pq.isEmpty()){
		int[] top = pq.remove();
		int price = top[0], city = top[1], stop = top[2];
		if(city == dst) return price;
		if(stop >= 0){
			Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
			for(int a: adj.keySet()){
				pq.offer(new int[]{price+adj.get(a), a, stop-1});
			}
		}
	}
	return -1;
    }
}
