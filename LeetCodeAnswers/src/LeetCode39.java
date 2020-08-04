/* author@ Qian Cai
 * Title@ Combination Sum
 * 
 */
import java.util.*;
public class LeetCode39 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        backtrack(0, candidates, target, 0, new ArrayList(), res);
        return res;
    }
    public void backtrack(int idx, int[] candidates, int target, int sum, List<Integer> list, List<List<Integer>> res){
        if (sum == target){
            res.add(new ArrayList(list));
        }
        for (int j = idx; j < candidates.length; j++){
            if (sum + candidates[j] <= target){
                list.add(candidates[j]);
                backtrack(j, candidates, target, sum+candidates[j], list, res);
                list.remove(list.size()-1);
            }
        }
    }
}
