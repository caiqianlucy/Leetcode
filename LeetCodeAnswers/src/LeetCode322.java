/* author@ Qian Cai
 * Title@ Coin Change
 * 
 */
import java.util.*;
public class LeetCode322 {
	class Solution {
	    public int coinChange(int[] coins, int amount) {
	        int[] memo = new int[amount+1];
	        Arrays.fill(memo, Integer.MAX_VALUE);
	        memo[0] = 0;
	        return helper(coins, amount, memo);
	    }
	    public int helper(int[] coins, int amount, int[] memo){
	        if (amount < 0) return -1;
	        if (memo[amount] != Integer.MAX_VALUE) return memo[amount];
	        int temp = Integer.MAX_VALUE;
	        for (int i = 0; i < coins.length; i++){
	            if (coins[i] <= amount){
	                int res = helper(coins, amount-coins[i], memo);
	                if (res >= 0 && res < temp) temp = res;
	            }
	        }
	        memo[amount] = (temp == Integer.MAX_VALUE ? -1: temp+1);
	        return memo[amount];
	    }
	}
}
