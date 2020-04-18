/* author@ Qian Cai
 * Title@ Minimum start
 * Given an array of integers nums, you start with an 
 * initial positive value startValue.

In each iteration, you calculate the step by step sum of 
startValue plus elements in nums (from left to right).

Return the minimum positive value of startValue such that
 the step by step sum is never less than 1.
 * 
 */
public class LeetCode1413 {
	public int minStartValue(int[] nums) {
        int start = 1;
        for (int i = nums.length-1; i >= 0; i--){
            start = Math.max(1, start-nums[i]);
        }
        return start;
    }
}
