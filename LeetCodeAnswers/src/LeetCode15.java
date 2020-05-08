/* author@Qian Cai
 * Title@ 3Sum
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.
 * Sorting + Two Pointer
* Time Complexity: O(n^2)
* Space Complexity: O(1)
 */
import java.util.*;
public class LeetCode15 {
	public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        for (int i = 0; i < nums.length-2 && nums[i] <= 0; i++){
            if (i == 0 || nums[i-1] != nums[i]){
                twoSum(nums, i, res);
            }
        }
        return res;
    }
    public void twoSum(int[] nums, int i, List<List<Integer>> res){
        int left = i+1, right = nums.length-1;
        while (left < right){
            int sum = nums[i] + nums[left] + nums[right];
            if (sum < 0 || (left > i+1 && nums[left] == nums[left-1])) left++;
            else if (sum > 0 || (right < nums.length-1 && nums[right] == nums[right+1])) right--;
            else {
                res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
            }
        }
    }
}
