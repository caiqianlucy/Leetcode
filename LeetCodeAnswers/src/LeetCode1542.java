/* author@ Qian Cai
 * Title@ Find Longest Awesome Substring
 * Time: O(n)
 * space: O(1)
 */
import java.util.*;
public class LeetCode1542 {
	public int longestAwesome(String s) {
        int res = 0;
        int bitMask = 0;
        //every bit represent count of digits[0-9], 0 for even count, 1 for odd count
        Map<Integer, Integer> map = new HashMap(); //key: bitMask, val: earliest index 
        map.put(0, -1);
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            bitMask ^= (1 << (c-'0'));
            if (map.containsKey(bitMask)){
                res = Math.max(res, i-map.get(bitMask));
            }
            for (int d = 0; d <= 9; d++){
                int temp = bitMask ^ (1<<d); //allow one odd count for making palindrome
                if (map.containsKey(temp)) res = Math.max(res, i-map.get(temp));
            }
            if (!map.containsKey(bitMask)) map.put(bitMask, i);
        }
        
        return res;
        
    }
}
