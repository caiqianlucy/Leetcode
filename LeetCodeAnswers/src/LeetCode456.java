/* author@ Qian Cai
 * Title@ 132 Pattern
 * ==========================================
 * Solution
 * Time: O(n)
 * Space: O(n)
 * Using an array to keep the min_left boundary for each index
 * Then traverse from last using a decreasing stack, 
 * for each new element only need to look if there is an element in the stack smaller than it and 
 * greater than min_left
 * 
 * 
 */
import java.util.*;
public class LeetCode456 {
	public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack(); //keep monotonic decreasing stack
        int n = nums.length;
        int[] min_left = new int[n]; //find the smallest left element for given num[i]
        for (int i = 0; i < n; i++){
            if (i == 0) min_left[i] = nums[i];
            else min_left[i] = Math.min(min_left[i-1], nums[i]);
        }
        for (int i = n-1; i > 0; i--){
            while (!stack.isEmpty() && nums[i] >= stack.peek()){
                int next = stack.pop();
                if (next < nums[i] && next > min_left[i]) return true;
            }
            stack.push(nums[i]);
        }
        return false;
            
    }
}
