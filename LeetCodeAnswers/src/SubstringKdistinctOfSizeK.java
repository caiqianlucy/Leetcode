/* author@ Qian Cai
 * Title@ Substrings of size K with K distinct chars
 * Given a string s and an int k, return all unique substrings of s of 
 * size k with k distinct characters.
 * Time complexity: O(n)
 * Spce complexity: O(1)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
public class SubstringKdistinctOfSizeK {
    public static List<String> subStringKdistinctOfSizeK(String s, int k){  
    	Set<String> res = new HashSet(); //use set to quarantine unique string
    	int[] count = new int[26]; //count is the # of each char from 'a' to 'z'
    	if (s == null || s.length() < k) return new ArrayList();
    	int i = 0, distinct = 0;
    	while (i < k) {
    		if (count[s.charAt(i)-'a'] == 0) distinct++;
    		count[s.charAt(i++)-'a']++;
    	}
    	if (distinct == k) res.add(s.substring(0, i));
    	while (i < s.length()) {
    		if (count[s.charAt(i)-'a'] == 0) distinct++;
    		count[s.charAt(i)-'a']++;
    		count[s.charAt(i-k)-'a']--; //remove i-k to maintain size of k
    		if (count[s.charAt(i-k)-'a'] == 0) distinct--;
    		i++;
    		if (distinct == k) res.add(s.substring(i-k, i));
    	}
    	return new ArrayList(res);
    }
    public static void main(String[] args) {
   	 String s1 = "abcabc";
   	 String s2 = "abacab";
   	 System.out.println(subStringKdistinctOfSizeK(s1, 3));
   	 System.out.println(subStringKdistinctOfSizeK(s2, 3));
   	 
    }
}
