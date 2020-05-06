/* author@ Qian Cai
 * Title@ Design Tic-Tac-Toe
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Time Complexity: each move step O(1)
 * Space complexity: O(n^n)
 */
public class TicTacToe {
    int[][] board;
    int[][] rows;
    int[][] cols;
    int[] diagnol;
    int[] antidiagnol;
    int n;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        rows = new int[n][2];
        cols = new int[n][2];
        diagnol = new int[2];
        antidiagnol = new int[2];
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        rows[row][player-1]++;
        if (rows[row][player-1] == n) return player;
        cols[col][player-1]++;
        if (cols[col][player-1] == n) return player;
        if (row == col){
            diagnol[player-1]++;
            if (diagnol[player-1] == n) return player;
        }
        if (row + col == n-1){
            antidiagnol[player-1]++;
            if (antidiagnol[player-1] == n) return player;
        }
        return 0;
    }
    
}
