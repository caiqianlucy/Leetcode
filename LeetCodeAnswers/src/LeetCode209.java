/* author@ Qian Cai
 * title@ Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s, find the minimal
 * length of a contiguous subarray of which the sum ≥ s. If there isn't one,
 * return 0 instead.
 * =================================
 * Solution1:
 * Two Pointer 
 * time: O(n) space: O(1)
 * 
 * Follow up: If you have figured out the O(n) solution, try coding another solution 
 * of which the time complexity is O(n log n). 
 * Solution 2 binary search
 */
public class LeetCode209 {
	public int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0;
        int n = nums.length;
        int res = n+1;
        int sum = 0;
        while (right < n){
            sum += nums[right];
            while (sum >= s && left <= right){
                res = Math.min(res, right-left+1);
                sum -= nums[left++];
            }
            right++;
        }
        return res == n+1 ? 0: res;
        
    }
	class Solution {
	    public int minSubArrayLen(int s, int[] nums) {
	        if (nums == null || nums.length == 0) return 0;
	        int n = nums.length;
	        int[] presum = new int[n+1];
	        for (int i = 0; i < n; i++){
	            presum[i+1] = presum[i] + nums[i];
	        }
	        int res = n+1;
	        for (int right = 1; right <= n; right++){
	            int left = binarySearch(presum, right, presum[right]-s);
	            if (left != -1) res = Math.min(res, right-left);
	        }
	        return res == n+1 ? 0 : res;
	    }
	    public int binarySearch(int[] presum, int right, int target){
	        int lo = 0, hi = right;
	        if (presum[0] > target) return -1;
	        while (lo <= hi){
	            int mi = (lo + hi)/2;
	            if (presum[mi] <= target) lo = mi+1;
	            else hi = mi-1;
	        }
	        return hi;
	    }
	}
}
