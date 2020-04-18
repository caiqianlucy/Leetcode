/* author@ Qian Cai
 * Title@ String Matching in an Array
 * Given an array of string words. Return all strings in words which is 
 * substring of another word in any order. 

 * String words[i] is substring of words[j], if can be obtained removing
 * some characters to left and/or right side of words[j].
 * =====================================================================
 * Solution
 * Brute force method: Time Complexity: O(n^2)
 * =====================================================================
 */
import java.util.*;

public class LeetCode1408 {
	public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList();
        if (words.length == 1) return res;
        for (int i = 0; i < words.length; i++){
            int j = 0;
            boolean noMatch = true;
            while (noMatch && j < words.length){
                if (j != i && words[j].contains(words[i])){
                    noMatch = false;
                    res.add(words[i]);
                }
                j++;
            }
        }
        return res;
        
    }
}
