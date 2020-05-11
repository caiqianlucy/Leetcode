/* author@ Qian Cai
 * Title@ Word Search II
 * Given a 2D board and a list of words from the dictionary,
 *  find all words in the board.

Each word must be constructed from letters of sequentially 
adjacent cell, where "adjacent" cells are those horizontally
 or vertically neighboring. The same letter cell may not be
  used more than once in a word.
 * *Trie + backtrack
*Time complexity: (O(mn*(4^L)))
*Space complexity: O(k) k is number of words
 */
import java.util.*;
public class LeetCode212 {
	char[][] board;
    List<String> res;
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        res = new ArrayList();
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return res;
        Trie trie= new Trie();
        build(trie, words);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                char c = board[i][j];
                if (trie.children[c-'a'] != null){
                    backtrack(i, j, trie);
                }
            }
        }
        return res;
    }
    //backtrack to find match and add match to the res list
    public void backtrack(int i, int j, Trie par){
        char c = board[i][j];
        Trie node = par.children[c-'a'];
        if (node.word != null){
             res.add(node.word);
             node.word = null; //avoid repeat adding the same word
        }
        board[i][j] = '#'; //temporarily mark the cur letter in case of revisiting
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int d = 0; d < 4; d++){
            int row = i + dirs[d][0], col = j + dirs[d][1];
            if (row >= 0 && row < board.length && col >= 0 && col < board[0].length){
                  char next = board[row][col];
                
                if (next !='#' && node.children[next-'a'] != null){
                    backtrack(row, col, node);
                }
            }
        }
        board[i][j] = c;
    }
    
    //build Trie for words array
    public void build(Trie trie, String[] words){
        
        for (String word: words){
             Trie cur = trie;
             for (char c: word.toCharArray()){
                 if (cur.children[c-'a'] == null) cur.children[c-'a'] = new Trie();
                 cur = cur.children[c-'a'];
             }
            cur.word = word;
        }

    }
    class Trie{
       Trie[] children;
       String word;
       public Trie(){
           children = new Trie[26];
       }
    }
}
