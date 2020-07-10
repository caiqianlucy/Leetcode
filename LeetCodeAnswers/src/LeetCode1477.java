/* author@ Qian Cai
 * Title@ Find Two Non-overlapping Sub-arrays Each with Target Sum
 * Given an array of integers arr and an integer target.

You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.

Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 * 
 * 
 * 
 * 
 */
import java.util.*;
public class LeetCode1477 {
	public int minSumOfLengths(int[] arr, int target) {
        int res= Integer.MAX_VALUE;
        int best = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap(); //key: presum value: latest index
        map.put(0, -1);
        
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];       
            map.put(sum, i);
        }
        sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            if (map.containsKey(sum-target)){
                best = Math.min(best, i-map.get(sum-target));   
            }
            if (best < Integer.MAX_VALUE && map.containsKey(sum + target)){
                res = Math.min(res, best + map.get(sum + target) - i);
            }
            
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
