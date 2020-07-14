/* author@ Qian Cai
 * Title@ Split Array Largest Sum
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
 * ===============
 * time: O(n*log(sumOfArray))
 * space: O(1)
 */
public class LeetCode410 {
	public int splitArray(int[] nums, int m) {
        int lo = 0, hi = 0;
        for (int num: nums){
            lo = Math.max(num, lo);
            hi += num;
        }
        while (lo <= hi){
            int mi = lo + (hi-lo)/2;
            if (canSplit(nums, mi, m)) hi = mi-1;
            else lo = mi+1;
        }
        return lo;
    }
    public boolean canSplit(int[] nums, int sum, int m){
        int count = 1;
        int cur = 0;
        for (int i = 0; i < nums.length; i++){
            if (cur + nums[i] > sum){
                count++;
                cur = nums[i];
            } else {
                cur += nums[i];
            }          
        }
        return count <= m;
    }
}
