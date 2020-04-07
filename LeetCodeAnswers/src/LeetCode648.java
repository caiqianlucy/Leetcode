import java.util.List;

/*author@ Qian Cai
 * Title@ Replace words
 * Trie
* Time Complexicity:O(n) n is the length of the sentence
* Space Complexicity: O(n)
 */
public class LeetCode648 {
	public String replaceWords(List<String> dict, String sentence) {
        TrieNode trie = new TrieNode();
        for (String s: dict){
            TrieNode cur = trie;
            for (char letter: s.toCharArray()){
                if (cur.children[letter-'a'] == null){
                    cur.children[letter-'a'] = new TrieNode();
                }
                cur = cur.children[letter-'a'];
            }
            cur.word = s;
        }
        StringBuilder ans = new StringBuilder();
        String[] words = sentence.split("\\s+");
        for (String word: words){
            if (ans.length() > 0) ans.append(" "); //add space between words
            TrieNode cur = trie;
            for (char c: word.toCharArray()){
                
                if (cur.children[c-'a'] == null || cur.word != null) break;
                cur = cur.children[c-'a'];
            }
            ans.append(cur.word != null ? cur.word: word);
        }
        return ans.toString();
        
    }
    class TrieNode{
        TrieNode[] children;
        String word; //stores the word if it is the end
        TrieNode(){
            children = new TrieNode[26];
        }
    }
}
