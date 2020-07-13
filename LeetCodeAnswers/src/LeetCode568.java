/* author@ Qian Cai
 * Title@ Maximum Vacation Days
 * ===============================================
 * Solution
 * Dynamic Programming: O(KN^2)  Space O(KN)
in a sparse edge graph, could build a graph first to avoid traversing all the nodes to find the edges. (O(KM + N^2) M is the average edge number for each node
Bottom up
state: dp[from][w] max vacation could take for start from city from at week w
initialize:  dp[0-N][k] = 0
transition: for every city, you could 
                 stay there dp[from][w] = Math.max(dp[from][w], dp[from][w+1]+ days[from][w])
                 or fly to city where flights[from][to] == 1,  dp[from][w] = Math.max(dp[from][w], dp[to][w+1]+ days[to][w])
 
 
answer: dp[0][0]

 * 
 */
public class LeetCode568 {
	public int maxVactionDays(int[][] flights, int[][] days){
		int n = flights.length, k = days[0].length;
		int[][] dp = new int[n][k+1];
		for (int w = k-1; w >= 0; w--){
			for (int from = 0; from < n; from++){
				for (int to = 0; to < n; to++){
					if (from == to || flights[from][to] == 1){
						dp[from][w] = Math.max(dp[from][w], days[to][w] + dp[to][w+1]);
					}
				}
			}
		}
		return dp[0][0];
	}

}
