/* author@ Qian Cai
 * Title@ Word Break II
 * Given a non-empty string s and a dictionary wordDict 
 * containing a list of non-empty words, add spaces in s
 *  to construct a sentence where each word is a valid
 *   dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple
 times in the segmentation.
You may assume the dictionary does not contain duplicate 
words.
 * Time: O(n^3)
 * 
 */
import java.util.*;
public class LeetCode140 {
	public List<String> wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}
