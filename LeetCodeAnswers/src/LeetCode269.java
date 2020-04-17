/*author@ Qian Cai
 * Title@ Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, 
 * the order among letters are unknown to you. You receive a list of non-empty
 *  words from the dictionary, where words are sorted lexicographically by the
 *   rules of this new language. Derive the order of letters in this language.
 * ==========================
 * dfs
 * Time: O(c), c is the length of all the word in the words array
 * ==========================
 */
import java.util.*;
public class LeetCode269 {  
	Map<Character, Set<Character>> reverseAdjList = new HashMap();
    Map<Character, Boolean> seen = new HashMap(); //color whether the char is in the loop
    StringBuilder res = new StringBuilder();
    public String alienOrder(String[] words) {
        for (String word: words){
            for (char c: word.toCharArray()){
                reverseAdjList.putIfAbsent(c, new HashSet());
            }
        }
        for (int i = 0; i < words.length-1; i++){
            for (int j = 0; j < Math.min(words[i].length(), words[i+1].length()); j++){
                if (words[i].startsWith(words[i+1]) && words[i].length() > words[i+1].length()) return "";
                if (words[i].charAt(j) != words[i+1].charAt(j)){
                    reverseAdjList.get(words[i+1].charAt(j)).add(words[i].charAt(j));
                    break;
                }
            }
        }
        for (char key: reverseAdjList.keySet()){
            boolean cycle = dfs(key); 
            if (cycle) return "";     
        }
        //if (res.length() < reverseAdjList.size()) return "";
        return res.toString();
    }
    public boolean dfs(char c){
        if (seen.containsKey(c)) return seen.get(c);
        seen.put(c, true);
        for (char nei: reverseAdjList.get(c)){
            boolean cycle = dfs(nei);
            if (cycle) return true;
        }
        seen.put(c, false);
        res.append(c);
        return false;
    }
}
