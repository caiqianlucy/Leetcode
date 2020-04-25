/* author@ Qian Cai
 * Title@ Next Permutation
 * Implement next permutation, which rearranges numbers into the 
 * lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest 
possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its 
corresponding outputs are in the right-hand column.
 *Time complexity: O(n) Space complexity: O(1)
 *
 */
public class LeetCode74 {
	public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        int firstDec = n-2;
        while (firstDec >= 0 && nums[firstDec] >= nums[firstDec+1]){
            firstDec--;
        }
        if (firstDec == -1) {
            reverse(nums, 0);
            return;
        }
        int i = n-1;
        while(nums[i] <= nums[firstDec]){
            i--;
        }
        //System.out.println("firstDec is " + firstDec + " i is " + i); 
        swap(nums, firstDec, i);
        reverse(nums, firstDec+1);
        
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums, int i){
        int j = nums.length-1;
        while (i < j){
            swap(nums, i++, j--);
        }
    }
}
