/* author@ Qian Cai
 * Title@ Best Time to Buy and Sell Stock III
 * Say you have an array for which the ith element is the price of a given
 *  stock on day i. Design an algorithm to find the maximum profit. 
 *  You may complete at most two transactions.
 *  Note: You may not engage in multiple transactions at the same time 
 *  (i.e., you must sell the stock before you buy again).
 * Time Complexity O(n) Space Complexity O(1) 
 */
public class LeetCode123 {
	public int maxProfit(int[] prices){
	    int buyOne = Integer.MAX_VALUE, buyTwo = Integer.MAX_VALUE;
	    int sellOne = 0, sellTwo = 0;
	    for (int price: prices){
	         buyOne = Math.min(price, buyOne);
	         sellOne = Math.max(sellOne, price-buyOne);
	         buyTwo = Math.min(buyTwo, price-sellOne);
	         sellTwo = Math.max(sellTwo, price-buyTwo);
	    }
	    return sellTwo;
	}
}
