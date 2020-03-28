/*author@Qian Cai
 * time: O(logn), space: O(1)
 * Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).
 */
public class LeetCode33 {
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0: -1;
        int smallestIdx = helper(nums);
        if (smallestIdx == 0) return binarySearch(nums, 0, nums.length-1, target);
        if (target > nums[nums.length-1]) return binarySearch(nums, 0, smallestIdx-1, target);
        return binarySearch(nums, smallestIdx, nums.length-1, target);
    }
    private int helper(int[] nums){
        if (nums[0] < nums[nums.length-1]) return 0;
        int l = 0, r = nums.length-1;
        while (l <= r){
            int mi = l + (r-l)/2;
            if (nums[mi] > nums[mi+1]) return mi+1;
            else{
                if (nums[mi] < nums[l]) r = mi-1;
                else l = mi+1;
            }         
        }
        return 0;
    }
    private int binarySearch(int[] nums,  int l, int r, int target){
        if (target < nums[l] || target > nums[r]) return -1;
        while (l <= r){
            int mi = l + (r-l)/2;
            if (nums[mi] == target) return mi;
            else if (nums[mi] > target) r = mi-1;
            else l = mi+1;
        }
        return -1;
    }
}
