/*author@ Qian Cai
 *Title@ Minimum Subsequence in Non-Increasing Order
 *Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater than the sum of the non included elements in such subsequence. 

If there are multiple solutions, return the subsequence with minimum size and if there still exist multiple solutions, return the subsequence with the maximum total sum of all its elements. A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array. 

Note that the solution with the given constraints is guaranteed to be unique. Also return the answer sorted in non-increasing order.
 * Time: O(nlogn)
 */
import java.util.*;
public class LeetCode1403 {
	public List<Integer> minSubsequence(int[] nums) {
        List<Integer> res = new LinkedList();
        Arrays.sort(nums);
        int sum = 0;
        for (int n: nums) sum+=n;
        int cur = 0;
        for (int i = nums.length-1; i>=0; i--){
            cur += nums[i];
            res.add(nums[i]);
            if (cur > sum-cur) return res;
        }
        return res;
    }
}
