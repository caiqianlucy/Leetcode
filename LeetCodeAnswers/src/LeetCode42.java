/* author@ Qian Cai
 * Title@ Trapping Rain Water
 * Given n non-negative integers representing an elevation map 
 * where the width of each bar is 1, compute how much water it is 
 * able to trap after raining.
 * Solution
 * Two Pointer
 * Time O(n) Space O(1)
 */
public class LeetCode42 {
	//Use two pointer to keep the boundary for each region
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int leftMax = 0, rightMax = 0;
        int left = 0;
        int right = n-1;
        int res = 0;
        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= leftMax){
                    leftMax = height[left];
                } else res += leftMax-height[left];
                left++;
            } else{
                if (height[right] >= rightMax){
                    rightMax = height[right];
                } else res += rightMax-height[right];
                right--;
            }
        }
        return res;
    }
}
