/*author@ Qian Cai
 * Title@ Queries on a permutation with Key
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to i=queries.length-1) according to the following rules:

In the beginning, you have the permutation P=[1,2,3,...,m].
For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
Return an array containing the result for the given queries.
 * ============================================================================
 * Solution
 * (1) Method1: brute-force:
 * Time Complexity: O(n^2) Space Complexity: O (n)
 * (2) Method2: 
 * create a fenwick array with length m+n+1, where m is the number range,
 * n is the length of queries
 * a. initially locate number[1,m] to position[n+1, n+m] with value 1
 * b. for each query, relocate queries[i] to position n-i-1 
 * c. prefix sum of queries[i] tells us how many numbers are before queries[i]
 * Time complexity: O(nlog(n+m)), Space(O(n+m))
 * ============================================================================
 */
import java.util.List;
import java.util.ArrayList;
public class LeetCode1409 {
	class Solution {
	    public int[] processQueries(int[] queries, int m) {
	        List<Integer> list = new ArrayList();
	        for (int i = 1; i <= m; i++) list.add(i);
	        int[] res = new int[queries.length];
	        
	        for(int i = 0; i < queries.length; i++){
	            for (int j = 0; j < list.size(); j++){
	                if (list.get(j) == queries[i]){
	                    res[i] = j;
	                    list.remove(j);
	                    list.add(0, queries[i]);
	                }
	            }
	        }
	        return res;
	    }
	}
	public int[] processQueriesBIT(int[] queries, int m) {
        int n = queries.length;
        BIT bit = new BIT(m+n+1);
        Map<Integer, Integer> map = new HashMap(); //position of each integer in the array
        for (int i = 1; i <= m; i++){
            bit.update(n+i, 1);
            map.put(i, n+i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++){
            int index = map.remove(queries[i]);
            res[i] = bit.get(index-1);
            bit.update(index,-1);
            bit.update(n-i, 1);
            map.put(queries[i], n-i);
        }
        return res;
    }
    class BIT{
        int[] array;
        int n;
        public BIT(int n){
            array = new int[n];
            this.n = n;
        }
        public void update(int idx, int delta){
            while (idx < n){
                array[idx] += delta;
                idx += idx&(-idx);
            }
        }
        public int get(int idx){
            int res = 0;
            while (idx > 0){
                res += array[idx];
                idx -= idx&(-idx);
            }
            return res;
        }
    }
}
