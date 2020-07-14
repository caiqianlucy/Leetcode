/* author@ Qian Cai
 * Title@ Stream of Characters
 * Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the
 given words.
query(letter): returns true if and only if for some k >= 1, the 
last k characters queried (in order from oldest to newest, 
including this letter just queried) spell one of the words in the
 given list.
 * 
 */
public class LeetCode1032 {
	class StreamChecker {
	    TrieNode root;
	    StringBuilder sb;
	    int k = 0;
	    public StreamChecker(String[] words) {
	        root = new TrieNode();
	        sb = new StringBuilder();
	        
	        for (String word: words){
	            k = Math.max(k, word.length());
	            //System.out.println(k);
	            TrieNode cur = root;
	            char[] charArray = word.toCharArray();
	            for (int i = charArray.length-1; i >= 0; i--){
	                char c = charArray[i];
	                if (cur.childrens[c-'a'] == null){
	                    cur.childrens[c-'a'] = new TrieNode();
	                }
	                cur = cur.childrens[c-'a'];
	            }
	            cur.isEnd = true;
	        }
	        //System.out.println(k);
	    }
	    
	    public boolean query(char letter) {
	        //System.out.println(k);
	        sb.insert(0, letter);
	        while (sb.length() > 0 && sb.length() > k) sb.deleteCharAt(sb.length()-1);
	        int count = 0; //count of how many k matches the word in the list
	        TrieNode cur = root;
	        for (int i = 0; i < sb.length(); i++){
	            char c = sb.toString().charAt(i);
	            if (cur.childrens[c-'a'] == null) return count >= 1;
	            cur = cur.childrens[c-'a'];
	            if (cur.isEnd) count++;
	        }
	        return count >= 1;
	        
	    }
	    class TrieNode{
	        TrieNode[] childrens;
	        boolean isEnd;
	        public TrieNode(){
	            childrens = new TrieNode[26];
	            isEnd = false;
	        }      
	    }
	}
}
