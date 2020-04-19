/* author@ Qian Cai
 * Title@ Minimum Number of Frogs Croaking
 * Given the string croakOfFrogs, which represents a combination of the 
 * string "croak" from different frogs, that is, multiple frogs can croak 
 * at the same time, so multiple “croak” are mixed. Return the minimum number 
 * of different frogs to finish all the croak in the given string.

A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ 
sequentially. The frogs have to print all five letters to finish a croak. 
If the given string is not a combination of valid "croak" return -1.
 * Time : O(n)
 * Space: O(1)
 */
import java.util.*;
public class LeetCode1419 {
	public int minNumberOfFrogs(String croakOfFrogs) {
        int[] countMap = new int[5];
        Map<Character, Integer> map = new HashMap();
        map.put('c', 0);
        map.put('r', 1);
        map.put('o', 2);
        map.put('a', 3);
        map.put('k', 4);
        int i = 0;
        int frogTotal = 0;
        int frogFinished = 0;
        while (i < croakOfFrogs.length()){
            char c = croakOfFrogs.charAt(i++);
            if (!map.containsKey(c)) return -1;
            int idx = map.get(c);
            if (idx == 0){
                if (frogFinished > 0) frogFinished--;
                else frogTotal++;
            }
            if (idx > 0 && countMap[idx] == countMap[idx-1]) return -1;
            if (idx == 4){
                frogFinished++;
            }
            countMap[idx]++;
        }
        
        for (int j = 1; j < 5; j++){
            if (countMap[j] != countMap[j-1]) return -1;
        }
        return frogTotal;
    }
}
