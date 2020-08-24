/* author@ Qian Cai
 * Title@ Subsets
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.
 * 
 */
import java.util.*;
public class LeetCode78 {
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        helper(0, nums, new ArrayList(), res);
        return res;
    }
    public void helper(int idx, int[] nums, List<Integer> list, List<List<Integer>> res){
       
        if (idx == nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        };
        helper(idx+1, nums, list, res);
        list.add(nums[idx]);
        helper(idx+1, nums, list, res);
        list.remove(list.size()-1);
    }
}
