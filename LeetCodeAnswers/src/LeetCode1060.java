/* author@ Qian Cai
 * Title@ Missing Element in Sorted Array
 * Given a sorted array A of unique numbers, find the K-th missing number starting 
 * from the leftmost number of the array.
 * time: O(n) space: O(1)
 */
public class LeetCode1060 {
	public int missingElement(int[] nums, int k) {
        int prev = 0;
        for (int i = 1; i < nums.length; i++){
            if (prev + nums[i]-nums[i-1] - 1 >= k){
                return nums[i-1] + (k-prev);
            } else {
                prev += nums[i] - nums[i-1] -1;
            }
        }
        return nums[nums.length - 1] + k - prev;
    }
}
