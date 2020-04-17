/* author@ Qian Cai
 * Title@ Verifying an Alien Dictionary
 * In an alien language, surprisingly they also use english lowercase letters,
 *  but possibly in a different order. The order of the alphabet is some 
 *  permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of 
the alphabet, return true if and only if the given words are sorted 
lexicographicaly in this alien language.
 * 
 */
public class LeetCode953 {
	public boolean isAlienSorted(String[] words, String order) {
        int[] orderArray = new int[26];
        for (int i = 0; i < 26; i++){
            orderArray[order.charAt(i)-'a'] = i;
        }
        for (int i = 0; i < words.length-1; i++){
            if (compare(words[i], words[i+1], orderArray) > 0) return false;
        }
        return true;
    }
    public int compare(String s, String t, int[] orderArray){
        for (int i = 0; i < Math.min(s.length(), t.length()); i++){
            if (orderArray[s.charAt(i)-'a'] > orderArray[t.charAt(i)-'a']) return 1;
            if (orderArray[s.charAt(i)-'a'] < orderArray[t.charAt(i)-'a']) return -1;
        }
        return s.length() == t.length() ? 0 : s.length() > t.length() ? 1: -1;
    }
}
