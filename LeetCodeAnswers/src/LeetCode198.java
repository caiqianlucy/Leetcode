/*author@ Qian Cai
 * Title@ House Robber
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * Time:O(n) Space: O(1)
 */
public class LeetCode198 {
	public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int noPre = 0, yesPre = 0;//noPrev don't rob previous house, yesPrev rob previous house
        for (int i = 0; i < nums.length; i++){
            int temp = noPre;
            noPre = Math.max(yesPre, noPre);
            yesPre = temp + nums[i];
        }
        return Math.max(noPre, yesPre);
    }
}
