/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock with Transaction Fee
 * Time complexity O(n) space complexity O(1)
 * 
 */
public class LeetCode714 {
	public int maxProfit(int[] prices, int fee) {
        int sell = 0, buy = -prices[0];
     for (int i = 1; i < prices.length; i++){
           sell = Math.max(sell, prices[i]+buy-fee);
           buy = Math.max(buy, sell-prices[i]);
     }
     return sell;

    }
}
