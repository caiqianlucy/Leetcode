/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock with Cooldown
 * Say you have an array for which the ith element is the price of a given 
 * stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many 
 * transactions as you like (ie, buy one and sell one share of the stock 
 * multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. 
 * (ie, cooldown 1 day)
 * Time Complexity O(n) Space Complexity O(1)
 */
public class LeetCode309 {
	public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int buy = -prices[0], sell = 0, cooldown = 0;
        for (int i = 1; i < prices.length; i++){
            buy = Math.max(cooldown-prices[i], buy);
            cooldown = Math.max(cooldown, sell);
            sell = Math.max(sell, prices[i]+buy);
        }
        return Math.max(sell, cooldown);
   }
}
