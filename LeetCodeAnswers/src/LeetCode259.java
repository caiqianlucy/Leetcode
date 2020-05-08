/* author@ Qian Cai
 * Title@ 3Sum Smaller
 * Given an array of n integers nums and a target, 
 * find the number of index triplets i, j, k 
 * with 0 <= i < j < k < n that satisfy the condition
 *  nums[i] + nums[j] + nums[k] < target.
 * 
 * //Sorting + Two Pointer
// Time: O(n^2) Space: O(1)
 */
import java.util.Arrays;
public class LeetCode259 {
	public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length-2 && 3*nums[i] < target; i++){
            sum += twoSumSmaller(nums, i, target);
        }
        return sum;
    }
    public int twoSumSmaller(int[] nums, int i, int target){
        int l = i+1, r = nums.length-1;
        int sum = 0;
        while (l < r){
            if (nums[l] + nums[r] < target-nums[i]){
                sum += r-l;
                l++;
            } else {
                r--;
            }
        }
        return sum;
    }
}
