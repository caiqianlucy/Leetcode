/*Author@ Qian Cai
 * Title@ Paint House
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
 * Time: O(n), Space: O(1)
 */

public class LeetCode256 {
	public int minCost(int[][] costs) {
        int[] prev = new int[3]; //prev[0], prev[1], prev[2] represents minimum costs for previous house in red, blue and green 
        for (int i = 0; i < costs.length; i++){
            int[] cur = costs[i];
            int[] temp = new int[3];
            temp[0] = cur[0] + Math.min(prev[1], prev[2]);
            temp[1] = cur[1] + Math.min(prev[0], prev[2]);
            temp[2] = cur[2] + Math.min(prev[0], prev[1]);
            prev = temp;
        }
        return Math.min(prev[0], Math.min(prev[1], prev[2]));
    }
}
