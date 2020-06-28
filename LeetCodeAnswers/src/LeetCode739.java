/* author@ Qian Cai
 * Title@ Daily Temperatures
 * Given a list of daily temperatures T, return a list such that, for each day in the input, 
 * tells you how many days you would have to wait until a warmer temperature. If there is no 
 * future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output 
should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an 
integer in the range [30, 100].
 * ===============================================================
 * Solution:
 * Monotonic stack
 * Time, Space: O(n)
 * 
 */
import java.util.*;
public class LeetCode739 {
	public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return T;      
        int n = T.length;
        Stack<Integer> stack = new Stack(); // monotonic decreasing order of index
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            res[i] = stack.isEmpty()? 0: (stack.peek()-i);
            stack.push(i);
        }
        return res;
        
    }
}
