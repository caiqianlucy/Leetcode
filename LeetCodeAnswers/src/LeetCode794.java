/* author@ Qian Cai
 * Title@ Valid Tic-Tac-Toe State
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
 * 
 */
public class LeetCode794 {
	public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String s: board){
            for (char c: s.toCharArray()){
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }
        }
       if (xCount != oCount && xCount != oCount + 1) return false;
       if (win('X', board) && xCount != oCount+1) return false;
       if (win('O', board) && xCount != oCount) return false;
       return true;
   }
   public boolean win(char c, String[] board){
       for (int i = 0; i < 3; i++){
           if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2) && board[i].charAt(1) == c) return true; //check row
           if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i) && board[1].charAt(i)  == c) return true; //check col
       }
       if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1)== board[2].charAt(2) && board[1].charAt(1)== c) return true;
       if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0) && board[1].charAt(1) == c) return true;
       return false;
   }
}
