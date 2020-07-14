/* author@ Qian Cai
 * Title@ Number Of Matching Subsequences
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
 * 
 */
import java.util.*;
public class LeetCode792 {
	/* we only want to traverse S once since S can be very long
    time: O(S.length + sum of(words[i].length))
    space: O(words.length)
    */
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        List<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; i++) heads[i] = new ArrayList<Node>();
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            heads[word.charAt(0)-'a'].add(new Node(i, 0));
        }
        for (char c: S.toCharArray()){
            List<Node> old_bucket = heads[c-'a'];
            heads[c-'a'] = new ArrayList<Node>(); //open new node list for next level
            for (Node node: old_bucket){
                if (node.j == words[node.i].length()-1) ans++; //reach the end for this word, the word is subsequence of S
                else {
                    heads[words[node.i].charAt(node.j+1)-'a'].add(new Node(node.i, node.j+1));
                }
            }
        }
        return ans;
    }
    private class Node{
        int i; //i is index of the word in words array
        int j; //j is the index of the current char
        private Node(int i, int j){
            this.i = i;
            this.j = j;
        }
        
    }
}
