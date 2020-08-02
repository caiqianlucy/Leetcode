/* author@ Qian Cai
 * Title@ Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.
 */
import java.util.*;
public class LeetCode438 {
	//time: O(M+N)
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        if (m < n) return new ArrayList();
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (char c: p.toCharArray()) pCount[c-'a']++;
        List<Integer> res = new ArrayList();
        for (int i = 0; i < m; i++){
            sCount[s.charAt(i)-'a']++;
            if (i >= n) sCount[s.charAt(i-n) - 'a']--;
            if (i >= n-1 && Arrays.equals(pCount, sCount)) res.add(i-n+1);
        }
        return res;
    }
}
