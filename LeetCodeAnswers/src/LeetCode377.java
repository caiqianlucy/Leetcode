/* author@ Qian Cai
 * Title@ Combination Sum IV
 * 
 */
import java.util.*;
public class LeetCode377 {
	 public int combinationSum4(int[] nums, int target) {
	        Map<Integer, Integer> memo = new HashMap();
	        return helper(nums, target, memo);
	    }
	    public int helper(int[] nums, int target, Map<Integer, Integer> memo){
	        if (target == 0) return 1;
	        if (target < 0) return 0;
	        if (!memo.containsKey(target)){
	            int res = 0;
	            for (int n: nums){
	                res += helper(nums, target-n, memo);
	            }
	            memo.put(target, res);
	        }
	        return memo.get(target);
	    }
}
