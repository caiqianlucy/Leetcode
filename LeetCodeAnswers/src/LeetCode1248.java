/*author@Qian Cai
 * Title@Count Number of Nice Subarrays
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.
Time: O(n) space: O(1) Similar as LeetCode992
 */
public class LeetCode1248 {
	 public int numberOfSubarrays(int[] nums, int k) {
	        return atMost(nums, k)-atMost(nums, k-1);
	    }
	    public int atMost(int[] nums, int k){
	        int i = 0, j = 0, cnt = 0, res = 0;
	        while (j < nums.length){
	            if (nums[j++] % 2 == 1) cnt++;
	            while (cnt > k){
	                if (nums[i++] % 2 == 1) cnt--;
	            }
	            res += j-i;
	        }
	        return res;
	    }
}
