/* author@ Qian Cai
 * Title@ Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 */
public class LeetCode300 {
	//Solution 1: O(n^2)
	class Solution {
	    public int lengthOfLIS(int[] nums) {
	        int n = nums.length;
	        int[] dp = new int[n];
	        for (int i = 0; i < n; i++){
	            int temp = 1;
	            for (int j = 0; j < i; j++){
	                if (nums[j] < nums[i]) temp = Math.max(temp, dp[j] + 1);
	            }
	            dp[i] = temp;
	        }
	        int res = 0;
	        for (int i = 0; i < n; i++) res = Math.max(dp[i], res);
	        return res;
	    }
	}
	//Solution 2: O(n)
	class Solution2 {
	    /*
	       [10,9,2,5,3,7,101,18]
	     i                    |  
	     j         |

	        2  3 7 18
	    */
	    public int lengthOfLIS(int[] nums) {
	        if (nums == null || nums.length == 0) return 0;
	        int n = nums.length;
	        int j = 0;
	        for (int i = 1; i < n; i++){
	            if (nums[i] > nums[j]) nums[++j] = nums[i];
	            else {
	                int idx = binarySearch(nums, j, nums[i]); //first nums[idx] >= nums[i]
	                nums[idx] = nums[i];
	            }
	        }
	        
	        return j+1;
	    }
	    public int binarySearch(int[] nums, int j, int target){
	        int left = 0, right = j;
	        while (left <= right){
	            int mi = left + (right-left)/2;
	            if (nums[mi] < target) left = mi+1;
	            else right = mi-1;
	        }
	        return left;
	    }
	}
}
