/* author@ Qian Cai
 * Title@ Validate Stack Sequence
 * Given two sequences pushed and popped with distinct
 *  values, return true if and only if this could have been the result of a sequence of 
 *  push and pop operations on an initially empty stack. 
 *  =============================================
 *  Solution1: time: O(n), space: O(n)
 *  Solution2: time: O(n), space: O(1)
 *  
 *  
 */
import java.util.*;
public class LeetCode946 {
	public boolean validateStackSequences1(int[] pushed, int[] popped) {
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
	public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0; //position of the stack element 
        int j = 0; //position of popped element
        for (int push: pushed){
            pushed[i] = push;
            while (i >= 0 && pushed[i] == popped[j]){
                i--;
                j++;                
            }
            i++;
        }
        return i == 0;
    }
}
