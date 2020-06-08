/* author@ Qian Cai
 * Title@ Validate Stack Sequence
 * Given two sequences pushed and popped with distinct
 *  values, return true if and only if this could have been the result of a sequence of 
 *  push and pop operations on an initially empty stack. 
 *  
 */
import java.util.*;
public class LeetCode946 {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack();
        int j = 0;
        for (int x: pushed){
            stack.push(x);
            while (!stack.isEmpty() && j < popped.length && stack.peek() == popped[j]){
                j++;
                stack.pop();
            }
        }
        return j == popped.length;
    }
}
