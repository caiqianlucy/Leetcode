/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock IV
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most 
 * k transactions.Note:
 * You may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * Time Complexity: O(nk) for (k < n/2) else: O(n) 
 * Space Complexity: O(k)/o(1)
 */
public class LeetCode188 {
	public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length < 2) return 0;
        if (k >= prices.length/2) return helper(prices);
        int[][] dp = new int[k][2];
        for (int i = 0; i < k; i++){
            dp[i][0] = Integer.MAX_VALUE; //buy cost
            dp[i][1] = 0; //sell earnings
        }
        for (int i = 0; i < prices.length; i++){
            for (int j = 0; j < k; j++){
                dp[j][0] = Math.min(dp[j][0], prices[i]-(j == 0 ? 0: dp[j-1][1]));
                dp[j][1] = Math.max(dp[j][1], prices[i] - dp[j][0]);
            }
        }
        return dp[k-1][1];
    }
    public int helper(int[] prices){
      int res = 0;
      int buyPrice = Integer.MAX_VALUE; //initiate the buyPrice as the largest integer number
      for (int price: prices){
          if (price > buyPrice)  res += price - buyPrice;
          buyPrice = price;
      }
      return res;
  }
}
