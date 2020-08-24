/* author@ Qian Cai
 * Title@ Jump Game II
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.
 * 
 */
public class LeetCode45 {
	public int jump(int[] nums) {
        int maxStep = 0;
        int beginStep = 0;
        int step = 0;
        while (maxStep < nums.length-1){
            int newMaxStep = maxStep;
            for (int i = beginStep; i <= maxStep; i++){
                newMaxStep = Math.max(newMaxStep, i+nums[i]);
            }
            step++;
            beginStep = maxStep+1;
            maxStep = newMaxStep;
        }
        return step;
    }
}
