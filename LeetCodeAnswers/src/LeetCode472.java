/* author@ Qian Cai
 * Title@ Concatenated Words
 * Given a list of words (without duplicates), please write 
 * a program that returns all concatenated words in the 
 * given list of words.
A concatenated word is defined as a string that is 
comprised entirely of at least two shorter words in the 
given array.
 * Time : O(n^2) space O(n)
 */
import java.util.*;
public class LeetCode472 {
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set =  new HashSet();
        for (String w: words) set.add(w);
        List<String> res = new ArrayList();
        Map<String, Boolean> memo = new HashMap(); //memorization of repeated string to avoid repeat 
        for (String w: words){
            if (isConcatenated(w, set, memo)){
                res.add(w);
            }
        }
        return res;
    }
    //helper to check whether a string can be concatenated with two strings 
    private boolean isConcatenated(String word, Set<String> set, Map<String, Boolean> memo){
        if (memo.containsKey(word)) return memo.get(word);
        for (int i = 1; i < word.length(); i++){
            if (set.contains(word.substring(0, i)) && (set.contains(word.substring(i)) || isConcatenated(word.substring(i), set, memo))) {
                memo.put(word, true);
                return true;
            }
            
        }
        memo.put(word, false);
        return false;
    }
}
