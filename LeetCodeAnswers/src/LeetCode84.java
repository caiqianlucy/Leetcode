/* author@ Qian Cai
 * Title@ Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height 
 * where the width of each bar is 1, find the area of largest rectangle in 
 * the histogram.
 * ===================================================================
 * Solution
 * Keep stack of idx in increasing order to record the
 */
import java.util.Stack;
public class LeetCode84 {
	public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < heights.length; i++){
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]){
                res = Math.max(res, heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        while (stack.peek()!= -1){
            res = Math.max(res, heights[stack.pop()]*(heights.length - stack.peek()-1));
        }                     
        return res;
    }
}
