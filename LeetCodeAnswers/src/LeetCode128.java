/* author@ Qian Cai
 * Title@ Longest Consecutive Sequence
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.
 * 
 */
import java.util.*;
public class LeetCode128 {
	public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet();
        int res = 0;
        for (int num: nums) set.add(num);
        for (int num: nums){
            if (!set.contains(num-1)){
                int cur = num;
                int count = 1;
                while (set.contains(cur+1)){
                    cur++;
                    count++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
