//Time: O(n) Spce: O(1)
public class LeetCode41 {
	public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean containsOne = isOneIn(nums);
        if (!containsOne) return 1;
        for (int i = 0; i < n; i++){
            if (nums[i] <= 0) nums[i] = 1;
        }
        for (int i = 0; i < n; i++){
            int idx = Math.abs(nums[i])-1;
            if (idx < n && nums[idx] > 0)  nums[idx] *= -1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i+1;
        }
        return n+1;
    }
    //find whether there is one present in the array
    public boolean isOneIn(int[] nums){
        for (int num: nums){
            if (num == 1) return true;
        }
        return false;
    }
}
