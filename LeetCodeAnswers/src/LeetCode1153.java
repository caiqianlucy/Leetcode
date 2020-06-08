/* author@ Qian Cai
 * Title@ String Transforms into Another String
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.
 * //graph problem
// one cannot match to several
//several can match to one
 */
import java.util.*;
public class LeetCode1153 {
	public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Map<Character, Character> map = new HashMap();
        
        for (int i = 0; i < str1.length(); i++){
            if (map.containsKey(str1.charAt(i)) && map.get(str1.charAt(i)) != str2.charAt(i)) return false;
            map.put(str1.charAt(i), str2.charAt(i));
        }
        return new HashSet<Character>(map.values()).size() < 26; //need a temp for transformation
    }
}
