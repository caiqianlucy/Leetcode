/*author@ Qian Cai
 * Title@ Find Pair With Given Sum
 * Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to a target - 30.

Conditions:

You will pick exactly 2 numbers.
You cannot pick the same element twice.
If you have muliple pairs, select the pair with the largest number.
 * Solution:
 * Use HashMap to record the each value and index, because for multiple pairs 
 * we select the pair with the largest number, also update the element with 
 * same value with the latest bigger index. 
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
public class FindPairWithGivenSum {
    public static int[] findPairWithSum(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap();
    	int[] res = new int[] {-1, -1};
    	int temp = Integer.MIN_VALUE;
    	for (int i = 0; i < nums.length; i++) {
    		if (map.containsKey(target-30-nums[i])) {
    			if (nums[i] > temp || target-30-nums[i] > temp) {
    				res[0] = i;
    				res[1] = map.get(target-30-nums[i]);
    				temp = Math.max(nums[i], target-30-nums[i]);
    			}
    		}
    		map.put(nums[i], i);
    	}
    	return res;
    }
    public static void main(String[] args) {
    	int[] nums1 = {1, 10, 25, 35, 60};
    	int target1 = 90;
    	System.out.println(Arrays.toString(findPairWithSum(nums1, target1)));
    	int[] nums2 = {20, 50, 40, 25, 30, 10};
    	int target2 = 90;
    	System.out.println(Arrays.toString(findPairWithSum(nums2, target2)));
    	int[] nums3 = {50, 20, 10, 40, 25, 30};
    	int target3 = 90;
    	System.out.println(Arrays.toString(findPairWithSum(nums3, target3)));
    	int[] nums4 = {1, 2};
    	int target4 = 90;
    	System.out.println(Arrays.toString(findPairWithSum(nums4, target4)));
    }
}
