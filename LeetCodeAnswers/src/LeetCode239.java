/* author@ Qian Cai
 * Title@ Sliding Window Maximum
 * Given an array nums, there is a sliding window of size 
 * k which is moving from the very left of the array to the
 *  very right. You can only see the k numbers in the window.
 *  Each time the sliding window moves right by one position. Return the max sliding window.
 * Time Complexity: O(n) Space Complexity: O(k)
 */
import java.util.*;
public class LeetCode239 {
	Deque<Integer> deq = new LinkedList();
    int[] nums;
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n*k == 0) return new int[0];
        if (k == 1) return nums;
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++){
            update(i, k);
            deq.addLast(i);
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int[] res = new int[n-k+1];
        res[0] = nums[max_idx];
        for (int i = k; i < n; i++){
           update(i, k);
           deq.addLast(i);
           res[i-k+1] = nums[deq.getFirst()];
        }
        return res;
    }
    public void update(int i, int k){
        if (!deq.isEmpty() && deq.getFirst() == i-k) deq.removeFirst();
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) deq.removeLast();
    }
}
