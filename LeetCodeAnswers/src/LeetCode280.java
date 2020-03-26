//Time: O(n), space: O(1)
public class LeetCode280 {
	public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n-1; i++){
            if (i % 2 == 0){
                if (nums[i] > nums[i+1]) swap(nums, i, i+1);
            }
            else {
                if (nums[i] < nums[i+1]) swap(nums, i, i+1);
            }
        }
    }
    public void swap(int[] nums, int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
