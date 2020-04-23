/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock II
 * Say you have an array prices for which the ith element is the price of a given stock 
 * on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions
 *  as you like (i.e., buy one and sell one share of the stock multiple times).
 *  Note: You may not engage in multiple transactions at the same time (i.e., you must sell 
 *  the stock before you buy again).
 * Time Complexity O(n) Space Complexity O(1) 
 */
public class LeetCode122 {
	public int maxProfit(int[] prices){
	      int res = 0;
	      int buyPrice = Integer.MAX_VALUE; //initiate the buyPrice as the largest integer number
	      for (int price: prices){
	          if (price > buyPrice)  res += price - buyPrice;
	          buyPrice = price;
	      }
	      return res;
	  }
}
