/* author@ Qian Cai
 * Title@ Shortest Subarray with Sum at Least K
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.
 * 
 * ===================================================================
 * Solution: 
 * Time, Space: O(n)
 * 
 */
import java.util.*;
public class LeetCode862 {
	public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] preSum = new long[n+1];
        for (int i = 0; i < n; i++){
            preSum[i+1] = preSum[i] + (long)A[i];
        }
        int ans = n+1;
        Deque<Integer> deque = new LinkedList(); //increasing stack, if p[j] <= p[i] && j > i (i is not gonna to be used)
        for (int i = 0; i < n+1; i++){
            while (!deque.isEmpty() && preSum[i] <= preSum[deque.getLast()]) deque.removeLast();
            while (!deque.isEmpty() && preSum[i] >= preSum[deque.getFirst()]+K) ans = Math.min(ans, i-deque.pollFirst());
            //System.out.println(ans);
            deque.addLast(i);
        }
        return ans == n+1 ? -1: ans;
        
    }
}
