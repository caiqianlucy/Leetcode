/* author@ Qian Cai
 * Title@ Candy Crush
 * This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.
================================================================================================
 * time O(mnk) m is row number, n is col number, k is total operation time to reach stable state
 * space: O(1)
 */
public class LeetCode723 {
	 int r, c;
	    public int[][] candyCrush(int[][] board) {
	        r = board.length;
	        c = board[0].length;
	        boolean finish = false; //there is more candy need to crush
	        while (!finish){
	            boolean finishRow = cleanRows(board);
	            boolean finishCol = cleanCols(board);
	            finish = finishRow && finishCol;
	            movedown(board);
	        }
	        return board;
	    }
	    public boolean cleanRows(int[][] board){
	        boolean finish = true;
	        for (int i = 0; i < r; i++){
	            for (int j = 0; j + 2 < c; j++){
	                int v = Math.abs(board[i][j]);
	                if (v != 0 && v == Math.abs(board[i][j+1]) && v == Math.abs(board[i][j+2])){
	                    board[i][j] = board[i][j+1] = board[i][j+2] = -v;
	                    finish = false;
	                }
	            }
	        }
	        return finish;
	    }
	    public boolean cleanCols(int[][] board){
	        boolean finish = true;
	        for (int j = 0; j < c; j++){
	            for (int i = 0; i+2 < r; i++){
	                int v = Math.abs(board[i][j]);
	                //System.out.println("i: " + i + " j: " + j + " v: " + v);
	                if (v != 0 && v == Math.abs(board[i+1][j]) && v == Math.abs(board[i+2][j])){
	                    board[i][j] = board[i+1][j] = board[i+2][j] = -v;
	                    finish = false;
	                    //System.out.println("i: " + i + " j: " + j + " v: " + v);
	                    //System.out.println(board[i+2][j]);
	                }
	            }
	        }
	        return finish;
	    }
	    public void movedown(int[][] board){
	        for (int j = 0; j < c; j++){
	            int ii = r-1;
	            for (int i = r-1; i >= 0; i--){
	                if (board[i][j] > 0){
	                    board[ii--][j] = board[i][j];
	                }
	            }
	            while (ii >= 0) board[ii--][j] = 0;
	        }
	    }
}
