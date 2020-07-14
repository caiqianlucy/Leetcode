/* author@ Qian Cai
 * Title@ Magic Squares In Grid
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct 
 * numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" 
subgrids are there?  (Each subgrid is contiguous).
 * 
 */
public class LeetCode840 {
	public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        if (m < 3 || n < 3) return 0;
        for (int i = 2; i < m; i++){
            for (int j = 2; j < n; j++){
                if (check(grid, i, j)) res++;
            }
        }
        return res;
    }
    public boolean check(int[][] grid, int i , int j){
        int[] rows = new int[3];
        int[] cols = new int[3];
        int[] count = new int[10];
        int dia = 0;
        int anti = 0;
        for (int r = i - 2; r <= i; r++){
            for (int c = j-2; c <= j; c++){
                if (grid[r][c] > 9 || grid[r][c] == 0) return false;
                if (++count[grid[r][c]] != 1) return false;
                
                rows[r-i+2] += grid[r][c];
                cols[c-j+2] += grid[r][c];
                if (r -i + 2 == c -j + 2) dia+= grid[r][c];
                if (r-i + 2 + c -j + 2 == 2) anti += grid[r][c];
            }
        }
        if (dia != anti) return false;
        for (int r = 0; r < 3; r++){
            if (rows[r] != dia || cols[r] != dia) return false;
        }
        return true;

    }
}
