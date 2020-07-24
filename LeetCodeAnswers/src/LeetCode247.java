/* author@ Qian Cai
 * Title@ Strobogrammatic Number II
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.
 * time: O(5^(n/2)) space: O(res) 
 */
import java.util.*;
public class LeetCode247 {
	Map<Character, Character> map;
    public List<String> findStrobogrammatic(int n) {
        map = new HashMap();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        List<String> res = new ArrayList();
        if (n % 2 == 0) helper(n, "", res);
        else {
            helper(n, "0", res);
            helper(n, "1", res);
            helper(n, "8", res);
        }
        return res;
    }
    public void helper(int n, String s, List<String> res){
        if (s.length() == n){
            if (s.charAt(0) != '0' || s.length() == 1){
                res.add(s);
            }
            return;
        }
        for (char key: map.keySet()){
            helper(n, key+s+map.get(key), res);
        }
    }
}
