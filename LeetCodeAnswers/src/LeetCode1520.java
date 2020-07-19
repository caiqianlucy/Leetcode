/* author@ Qian Cai
 * Title@ Maximum Number of Non-Overlapping Substrings
 * Solution:
 * time: o(n)
 * space: O(1)
 * 
 */
import java.util.*;
public class LeetCode1520 {
	public List<String> maxNumOfSubstrings(String s) {
        Map<Character, int[]> map = new HashMap();
        int len = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)) map.put(c, new int[]{i, i});
            else {
                map.get(c)[1] = i;
            }
        }
        List<int[]> ranges = new ArrayList();
        //Set<int[]> set = new HashSet();
        for (char key: map.keySet()){
            int[] range = map.get(key); // verify whether the range is valid for every char inside contained 
            boolean containsCharBefore = false; //if contains char appear before the start of the range already extended by previous run
            int start = range[0], end = range[1];
            for (int j = start; j <= end; j++){
                if (map.get(s.charAt(j))[0] < start){
                    containsCharBefore = true; 
                    break;
                }
                else end = Math.max(end, map.get(s.charAt(j))[1]);
            }
            if (!containsCharBefore) ranges.add(new int[]{start, end});
        }
        //for (int[] r: set) ranges.add(r); //deduplicate
        Collections.sort(ranges, (a, b)->((a[1]-b[1] == 0) ? b[0]-a[0]: a[1]-b[1]));
        List<String> res = new ArrayList();
        int end = -1;
        //one meeting room hold maximum meeting problem
        for (int[] range: ranges){
            if (range[0] > end){
                res.add(s.substring(range[0], range[1] + 1));
                end = range[1];
            }
        }
        
        return res;
    }
}
