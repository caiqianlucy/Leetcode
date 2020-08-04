/* author@ Qian Cai
 * Title@ Maximum Size Subarray Sum Equals k
 * Given an array nums and a target value k, find the maximum 
 * length of a subarray that sums to k. If there isn't one, return 0 instead.
 * ==============
 * Solution 1: hashmap time: O(n) space: O(n)
 * Solution 2: binary Search time: O(nlogn) space: O(1)
 * 
 */
import java.util.*;
public class LeetCode325 {
	class Solution1 {
	    public int maxSubArrayLen(int[] nums, int k) {
	        Map<Integer, Integer> map = new HashMap();
	        map.put(0, -1);
	        int sum = 0;
	        int res = 0;
	        for (int i = 0; i < nums.length; i++){
	            sum += nums[i];
	            if (map.containsKey(sum - k)){
	                res = Math.max(res, i - map.get(sum-k));
	            }
	            if (!map.containsKey(sum)) map.put(sum, i);
	        }
	        return res;
	    }
	}
}
