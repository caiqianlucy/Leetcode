/* author@ Qian Cai
 * Title@ Minimum Number of Refueling Stops
 * A car travels from a starting position to a destination which is target miles east of the starting position.

Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.

When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * Solution 1
 *  time: O(nlogn) space: O(n) priorityQueue
 *  Solution 2:
 *  time: O(n^2) space: O(n) dynamic programming
 *  
 */
import java.util.*;
public class LeetCode871 {
	class Solution1 {
	    public int minRefuelStops(int target, int startFuel, int[][] stations) {
	        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b)->(b-a));
	        //pq is a maxheap of gas station refeul capacity
	        int ans = 0, prev = 0;
	        int remain = startFuel;
	        for (int[] station: stations){
	            remain -= station[0]-prev;
	            //if cannot reach the station refuel at the stop has the maximum capacity
	            while (remain < 0 && !pq.isEmpty()){
	                remain += pq.poll();
	                ans++;
	            }
	            if (remain < 0) return -1;
	            pq.add(station[1]);
	            prev = station[0];
	        }
	        remain -= target - prev;
	        while (!pq.isEmpty() && remain < 0){
	            remain += pq.poll();
	            ans++;
	        }
	        if (remain < 0) return -1;
	        return ans;
	    }
	}
	class Solution2 {
	    
	    public int minRefuelStops(int target, int startFuel, int[][] stations) {
	        int N = stations.length;
	        long[] dp = new long[N + 1]; //dp[i], the farthest location we can get to using i refueling stops. 
	        dp[0] = startFuel;
	        for (int i = 0; i < N; ++i)
	            for (int t = i; t >= 0; --t)
	                if (dp[t] >= stations[i][0])
	                    dp[t+1] = Math.max(dp[t+1], dp[t] + (long) stations[i][1]);

	        for (int i = 0; i <= N; ++i)
	            if (dp[i] >= target) return i;
	        return -1;
	    }
	}
}
