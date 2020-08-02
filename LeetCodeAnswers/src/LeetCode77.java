/* author@ Qian Cai
 * title@ Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 */
import java.util.*;
public class LeetCode77 {
	//time: O(kn!/((n-k)!*k!))
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        backtrack(1, n, k, res, new ArrayList());
        return res;
    }
    public void backtrack(int i, int n, int k, List<List<Integer>> res, List<Integer> list){
        if (list.size() == k){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int j = i; j <= n; j++){
            list.add(j);
            backtrack(j+1, n, k, res, list);
            list.remove(list.size()-1);
        }
   }
}