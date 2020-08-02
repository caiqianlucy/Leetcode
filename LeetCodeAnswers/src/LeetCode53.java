/* author@ Qian Cai
 * Title@ Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum.
 */
public class LeetCode53 {
	public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE, sum = 0;
        for (int num: nums){
            sum = num + Math.max(sum, 0);
            res = Math.max(res, sum);
        }
        return res;
    }
}
