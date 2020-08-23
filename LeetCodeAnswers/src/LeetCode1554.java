/* author@ Qian Cai
 * Title@ Strings Differ by One character
 * Given a list of strings dict where all the strings are of the same length.

Return True if there are 2 strings that only differ by 1 character in the same index, otherwise return False.

Follow up: Could you solve this problem in O(n*m) where n is the length of dict and m is the length of each string.
 * 
 */
import java.util.*;
public class LeetCode1554 {
	public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet();
        for (String s: dict){
            int n = s.length();
            for (int i = 0; i < n; i++){
                String cs = s.substring(0, i) + "*" + s.substring(i+1);
                if (set.contains(cs)) return true;
                set.add(cs);
            }
        }
        return false;
    }
}
