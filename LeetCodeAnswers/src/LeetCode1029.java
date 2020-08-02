/* author@ Qian Cai
 * Title@ Two City Scheduling
 * There are 2N people a company is planning to interview. 
 * The cost of flying the i-th person to city A is costs[i][0], and the cost
 *  of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 * time: O(nlogn) space: O(1)
 */
import java.util.*;
public class LeetCode1029 {
	 public int twoCitySchedCost(int[][] costs) {
	       int sum = 0;
	       for (int i = 0; i < costs.length; i++){
	           sum += costs[i][0];
	       }
	      Arrays.sort(costs, (a, b)-> ((a[1]-a[0])-(b[1]-b[0])));
	        
	      for (int i = 0; i < costs.length/2; i++){
	          sum += costs[i][1]- costs[i][0];
	      }
	       return sum; 
	    }
}
