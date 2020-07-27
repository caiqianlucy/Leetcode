/* author@ Qian Cai
 * Title@ Longest Arithmetic Subsequence of Given Difference
 * Given an integer array arr and an integer difference, return the length of the longest 
 * subsequence in arr which is an arithmetic sequence such that the difference between 
 * adjacent elements in the subsequence equals difference.
 * 
 */
import java.util.*;
public class LeetCode1218 {
	public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> count = new HashMap(); //key: i, val: maximum length of subsequence start from i
        int n = arr.length;
        int res = 0;
        for (int i = n-1; i >= 0; i--){
            int curCount = 1;
            if (count.containsKey(arr[i]+difference)){
                curCount += count.get(arr[i] + difference);
            } 
            count.put(arr[i], curCount);
            res = Math.max(res, curCount);
        }
        return res;
    }
}
