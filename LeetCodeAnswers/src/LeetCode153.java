/* author@ Qian Cai
 * Title@ Find Minimum in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.
 * 
 * Both solution time: O(logn)
 */
public class LeetCode153 {
	//easier coding but takes more runs
	class Solution1 {
	    public int findMin(int[] nums) {
	        int left = 0, right = nums.length-1;
	        while (left < right){
	            int mi = left + (right-left)/2;
	            if (nums[mi] > nums[right]) left = mi+1;
	            else right = mi;
	        }
	        return nums[left];
	    }
	}
	//more checking but can terminate earlier
	class Solution2 {
		public int findMin(int[] nums) {
	        if (nums.length < 2) return nums[0];
	        int n = nums.length;
	        if (nums[0] < nums[n-1]) return nums[0];
	        int left = 0, right = n-1;
	        while (left <= right){
	            int mi = left + (right-left)/2;
	            if (mi != 0 && nums[mi] < nums[mi-1]) return nums[mi];
	            if (mi != n-1 && nums[mi] > nums[mi+1]) return nums[mi+1];
	            else if (nums[mi] > nums[0]) left = mi+1;
	            else right = mi-1;
	        }
	        return -1;
	    }
	}
}
