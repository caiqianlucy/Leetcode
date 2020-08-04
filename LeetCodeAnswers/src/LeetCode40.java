/* author@ Qian Cai
 * Title@ Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.
 * 
 */
import java.util.*;
public class LeetCode40 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        Arrays.sort(candidates);
        backtrack(res, list, candidates, target, 0, 0);
        return res;
    }
    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int idx, int sum){
        if (sum == target){
            res.add(new ArrayList(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++){
            if (sum + candidates[i] > target) break;
            if (i > idx && candidates[i] == candidates[i-1]) continue; //no duplicate combination
            list.add(candidates[i]);
            backtrack(res, list, candidates, target, i+1, sum + candidates[i]);
            list.remove(list.size()-1);
        }
    }
}
