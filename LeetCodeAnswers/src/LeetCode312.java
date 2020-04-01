/*author@ Qian Cai
 * title@ Burst Ballons
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

 * Time: O(n^3) space: O(n^2)
 */
public class LeetCode312 {
	 public int maxCoins(int[] nums) {
	        int n = nums.length;
	        int[] coins = new int[n+2];
	        int[][] dp = new int[n+2][n+2]; //dp stores coins earned for burst bollon (i, j) exclusive
	        //add a fake ballon with value 1 in front and after all the bollons
	        for (int i = 0; i < n; i++){
	            coins[i+1] = nums[i];
	        }
	        coins[0] = 1;
	        coins[n+1] = 1;
	        for (int left = n-1; left >= 0; left--){
	            for (int right = left+2; right < n+2; right++){
	                for (int i = left+1; i < right; i++){
	                    dp[left][right] = Math.max(dp[left][right], coins[i]*coins[left]*coins[right] + dp[left][i] + dp[i][right]);
	                }
	            }
	        }
	        return dp[0][n+1];
	    }
}
