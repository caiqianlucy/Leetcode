/*author@ Qian Cai
 * Title@ Count Largest Group
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits. 

Return how many groups have the largest size.
 * Time, Space: O(n)
 */
import java.util.*;
public class LeetCode1399 {
	public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap();
        int mCnt = 0, res = 0;
        for (int i = 1; i <= n; i++){
            int sum = helper(i);
            map.put(sum, map.getOrDefault(sum, 0)+1);
            mCnt = Math.max(mCnt, map.get(sum));
        }
        for (int key: map.keySet()){
            if (map.get(key) == mCnt){
                res++;
            }
        }
        return res;
    }
    public int helper(int n){
        int res = 0;
        while (n != 0){
            res += n%10;
            n/=10;
        }
        return res;
    }
}
