/* author@ Qian Cai
 * Title@ Combination Sum III
 * k(10-k)!/(10!*k!)
 * 
 */
import java.util.*;
public class LeetCode216 {
	public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList();
        backtrack(k, n, res, new ArrayList(), 1, 0);
        return res;
    }
    public void backtrack(int k, int n, List<List<Integer>> res, List<Integer> list, int i, int sum){
        if (list.size() == k){
            if (sum == n){
                res.add(new ArrayList(list));
                return;
            }
        }
        for (int j = i; j < 10; j++){
            list.add(j);
            backtrack(k, n, res, list, j+1, sum+j);
            list.remove(list.size()-1);
        }
    }
}
