/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell 
 * one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 * 
 * Time Complexity O(n) Space Complexity O(1) 
 */
public class LeetCode121 {
	public int maxProfit(int[] prices){
	      int res = 0;
	      int buyPrice = Integer.MAX_VALUE; //initiate the buyPrice as the largest integer number
	      for (int price: prices){
	          buyPrice = Math.min(price, buyPrice);
	          res = Math.max(res, price - buyPrice);
	      }
	      return res;
	  }
}
