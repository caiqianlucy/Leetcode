/* Author@ Qian Cai
 * Title@ Permutation Sequence
 * The set [1,2,3,...,n] contains a total of n! unique permutations.

 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
* Given n and k, return the kth permutation sequence.
 * ===========================================================================
 * Solution
 * for index at i there is (n-i-1)! combination for each possible value
 * find the order idx of kth number
 * remove that from list and append to result, now look the k-idx*comb th 
 * number following the same method
 * 
 * ============================================================================
 * Time， Space complexity: O(n) 
 */
import java.util.*;
public class LeetCode60 {
	public String getPermutation(int n, int k) {
        int comb = 1;
        for (int i = 1; i < n; i++) comb *= i;
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= n; i++) list.add(i);
        StringBuilder res =  new StringBuilder();
        --k; //the k the number is the array index k-1 number
        while (res.length() < n){
            int idx = k/comb;
            k-= idx*comb;
            res.append(list.remove(idx));
            
            if (res.length() < n) comb /= (n-res.length());
        }
        return res.toString();
    }
}
