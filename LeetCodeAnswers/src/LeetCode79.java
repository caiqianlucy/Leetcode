/* author@Qian Cai
 * Title@ Word Search
 * Given a 2D board and a word, find if the word exists 
 * in the grid.

The word can be constructed from letters of sequentially
 adjacent cell, where "adjacent" cells are those 
 horizontally or vertically neighboring. The same letter 
 cell may not be used more than once.
 * backtrack + "#" mark visited position
Time O(mn*(4^l)) Space: O(l)
 */
public class LeetCode79 {
	char[][] board;
    int m, n;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (backtrack(i, j, word, 0)) return true;
            }
        }
        return false;
    }
    public boolean backtrack(int row, int col, String word, int idx){
        if (idx == word.length()) return true;
        if (row < 0 || col < 0 || row >= this.m || col >= this.n || this.board[row][col] != word.charAt(idx)) return false;
        boolean match = false;
        this.board[row][col] = '#'; //Temporarily mark the current position to # as visited
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int d = 0; d < 4; d++){
            match = backtrack(row + dirs[d][0], col+dirs[d][1], word, idx+1);
            if (match) break;
        }
        board[row][col] = word.charAt(idx);
        return match;
        
    }
}
