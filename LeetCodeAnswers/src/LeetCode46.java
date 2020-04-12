/* Author@ Qian Cai
 * Title@ Permutations
 * Given a collection of distinct integers, return all possible permutations.
 * ===========================================================================
 * Solution
 * If the start index is n means the curent permutation is done
 * Iterate over the integers from index start to n-1
 *    Place ith integer to the first position
 *    proceed to create all permutation from i+1 position
 *    switch back the first and ith integer
 * ============================================================================
 * Time， Space complexity: O(n*n!) 
 */
import java.util.*;
public class LeetCode46 {
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new  ArrayList();
        for (int num: nums) list.add(num); 
        int n = nums.length;
        backtrack(0, n, list, res);
        return res;
    }
    public void backtrack(int start, int n, List<Integer> list, List<List<Integer>> res){
        if (start == n) res.add(new ArrayList<Integer>(list));
        for (int i = start; i < n; i++){
            swap(list, start, i); //place ith integer to the first position
            backtrack(start+1, n, list, res);
            swap(list, start, i); //switch it back
        }
    }
    public void swap(List<Integer> list, int i, int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        
    }
}
