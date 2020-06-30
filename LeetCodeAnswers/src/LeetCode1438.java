/* author@ Qian Cai
 * Title@ Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

 

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 * 
 */
import java.util.*;
public class LeetCode1438 {
	public int longestSubarray(int[] nums, int limit) {
        //keep two deque in ascending and decreasing order for checking min and max in the given range asc[0] to j and des[0] to j, deque stores index
        Deque<Integer> asc_deq = new LinkedList();
        Deque<Integer> des_deq = new LinkedList();
        int leftBoundary = -1;
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            while (!asc_deq.isEmpty() && nums[i] < nums[asc_deq.getLast()]) asc_deq.removeLast();
            while (!des_deq.isEmpty() && nums[i] > nums[des_deq.getLast()]) des_deq.removeLast();
            asc_deq.addLast(i);
            des_deq.addLast(i);
            while (nums[des_deq.getFirst()] - nums[asc_deq.getFirst()] > limit){
                if (des_deq.getFirst() > asc_deq.getFirst()){
                    leftBoundary = Math.max(leftBoundary, asc_deq.removeFirst());
                } else {
                    leftBoundary = Math.max(leftBoundary, des_deq.removeFirst());
                }
            }
            res = Math.max(res, i-leftBoundary);
        }
        return res;
    }
}
