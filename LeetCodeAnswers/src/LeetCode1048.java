/* author@ Qian Cai
 * Title@ Longest String Chain
 * Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.
 * time: O(nlogn + nm^2) n is the length of words, m is the average length for each word
 * Space: O(n)
 */
import java.util.*;
public class LeetCode1048 {
	public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap();
        Arrays.sort(words, (a, b)->(a.length() - b.length()));
        int res = 0;
        for (String word: words){
            int best = 0;
            for (int i = 0; i < word.length(); i++){
                String s = word.substring(0, i) + word.substring(i+1);
                best = Math.max(best, dp.getOrDefault(s, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
