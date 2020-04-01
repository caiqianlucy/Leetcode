/*author@Qian Cai
 * Title@ House Robber II
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * Time: O(n) Space: O(1)
 */
public class LeetCode213 {
	public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(robRange(nums, 0, nums.length-2), robRange(nums, 1, nums.length-1));
    }
    public int robRange(int[] nums, int i, int j) {
        if (nums == null || nums.length == 0) return 0;
        int noPre = 0, yesPre = 0;//noPrev don't rob previous house, yesPrev rob previous house
        for (int k = i; k <= j; k++){
            int temp = noPre;
            noPre = Math.max(yesPre, noPre);
            yesPre = temp + nums[k];
        }
        return Math.max(noPre, yesPre);
    }
}
