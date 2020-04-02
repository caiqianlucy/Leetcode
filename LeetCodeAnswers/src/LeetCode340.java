import java.util.HashMap;
import java.util.Map;

/*author@ Qian Cai
 * Title@ Longest Substring with At Most K Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * Time, Space: O(n)
 */
public class LeetCode340 {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        int i = 0, j = 0, cnt = 0, res = 0;
        Map<Character, Integer> map = new HashMap();
        while (j < s.length()){
            char c = s.charAt(j++);
            if (!map.containsKey(c)) {
                cnt++;
                map.put(c, 0);
            }
            map.put(c, map.get(c)+1);
             while (cnt > k){
                 char removeC = s.charAt(i++);
                 map.put(removeC, map.get(removeC)-1);
                 if (map.get(removeC) == 0) {
                     cnt--;
                     map.remove(removeC);
                 }
             }
            res =Math.max(res, j-i);
        }
        return res;
    }
}
