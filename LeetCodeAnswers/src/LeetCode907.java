/* author@Qian Cai
 * Title@Sum of Subarray Minimums
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) 
 * subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.
 */
import java.util.*;
public class LeetCode907 {
	//for every subarray we will count all subarrays with A[j] as the minimum number, the start idx is prev[j], the end idx is next[j]
    /*
         [71, 55, 82, 55]
    idx   0    1   2   3
    prev  -1   -1  1   -1
               330    82     220
               **duplicate situation calculate twice
    
    */
    public int sumSubarrayMins(int[] A) {
        int MOD = 1000000007;
        int n = A.length;
        int[] prev = new int[n];
        Stack<Integer> stack = new Stack(); //monotonic increasing stack of A[idx], stack stores idx
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && A[i] <= A[stack.peek()]) stack.pop();
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack = new Stack();
        long res = 0;
        for (int i = n-1; i >= 0; i--){
             while (!stack.isEmpty() && A[i] < A[stack.peek()]) stack.pop();
            int next = stack.isEmpty() ? n: stack.peek();
            res += (long)(i-prev[i])*((long)(next-i))%((long)MOD) * A[i] % ((long)MOD);
            res %= (long)MOD;
            stack.push(i);
        }
        return (int)res;
        
    }
}
