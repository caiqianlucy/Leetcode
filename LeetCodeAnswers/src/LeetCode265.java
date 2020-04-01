/*author@ Qian Cai
 * Title@ Paint House II
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
 * Time: O(nk) Space: O(1), Save minCol and secMinCol
 */
public class LeetCode265 {
	public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int prevMin = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE;
        int prevMinColor = 0;
        for (int j = 0; j < k; j++){
            int cost = costs[0][j];
             if (cost < prevMin){
                        secMin = prevMin;
                        prevMin = cost;
                        prevMinColor = j;
                    } else if (cost < secMin){
                        secMin = cost;
                    }  
        }
        for (int i = 1; i < n; i++){
            int minCur = Integer.MAX_VALUE, secCur = Integer.MAX_VALUE, idx = 0;
            for (int j = 0; j < k; j++){ 
                int cost = costs[i][j];
                 if (j == prevMinColor) cost += secMin;
                 else cost += prevMin;
                 if (cost < minCur){
                     secCur = minCur;
                     minCur = cost;
                     idx = j;
                 } else if (cost < secCur){
                     secCur = cost;
                 }
            }     
            prevMin = minCur;
            secMin = secCur;
            prevMinColor = idx;
        }
        return prevMin;
    }
}
