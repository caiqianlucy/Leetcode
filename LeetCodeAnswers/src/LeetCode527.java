/* author@ Qian Cai
 * Title@ Word Abbreviation
 * Time: O(n)
 * 
 */
import java.util.*;
public class LeetCode527 {
	public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, Integer> map = new HashMap(); //key: string, val: idx
        Map<String, List<String>> groups = new HashMap(); //abbreviation, list of word match the abbreviation
        for (int i = 0; i < dict.size(); i++){
            String ab = helper(dict.get(i), 0); //get ab from index 0
            map.put(dict.get(i), i);
            if (!groups.containsKey(ab)) groups.put(ab, new ArrayList());
            groups.get(ab).add(dict.get(i));
        }
        String[] res = new String[dict.size()];
        for (List<String> group: groups.values()){
            TrieNode root = new TrieNode();
            //build trie
            for (String word: group){
                TrieNode cur = root;
                for (char c: word.substring(1).toCharArray()){
                    if (cur.children[c-'a'] == null) cur.children[c-'a'] = new TrieNode();
                    cur.count++;
                    cur = cur.children[c-'a'];
                   
                }
            }
            //find the first distinct character in each word in the group
            for (String word: group){
                TrieNode cur = root;
                int i = 1;
                while (i < word.length()){
                    if (cur.count == 1) break;
                    cur = cur.children[word.charAt(i++)-'a'];
                }
                res[map.get(word)] = helper(word, i-1);
            }
        }
        return Arrays.asList(res);
    }
    public String helper(String word, int idx){
        //does not make the word shorter
        if (word.length() - idx <= 3) return word;
        return word.substring(0, idx+1) + (word.length()-idx-2) + word.substring(word.length()-1);
    }
    class TrieNode{
        TrieNode[] children;
        int count; //how many children it has
        TrieNode(){
            children = new TrieNode[26];
            count = 0;
        }
    }
}
