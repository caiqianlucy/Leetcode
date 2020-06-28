/* author@ Qian Cai
 * Title@ Next Greater Element II
 * Given a circular array (the next element of the last element is the first element of the array),
 *  print the Next Greater Number for every element. The Next Greater Number of a number x is the
 *   first greater number to its traversing-order next in the array, which means you could search 
 *   circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 * ==================================================================================
 * Solution
 * Using a monotonic decreasing for checking the next greater element. Because you only need to
 * check any element afterwards if it is greater than the prev element.
 * 
 * Time, Space: O(n)
 * 
 */
import java.util.Stack;
public class LeetCode503 {
	public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        Stack<Integer> stack = new Stack(); //keep a monotonic decreasing stack
        int n = nums.length;
        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            stack.push(nums[i]);
        }
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            res[i] = stack.isEmpty() ? -1: stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
