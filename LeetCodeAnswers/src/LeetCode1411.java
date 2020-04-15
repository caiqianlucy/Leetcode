/*author@Qian Cai
 * Title@ Number of ways to paint NX3 Grid
 * You have a grid of size n x 3 and you want to paint each cell of the grid 
 * with exactly one of the three colours: Red, Yellow or Green while making 
 * sure that no two adjacent cells have the same colour (i.e no two cells 
 * that share vertical or horizontal sides have the same colour).

You are given n the number of rows of the grid.

Return the number of ways you can paint this grid. As the answer may grow large,
 the answer must be computed modulo 10^9 + 7.
 * ==========================================================================
 * Solution
 * for each row, there are 12 states, initial for row 1, there are 6 XYX states
 * and 6 XYZ states
 * for each XYX states its next row has 3 XYX states and 2 XYZ states
 * for each XYZ states its next row has 2 XYX states and 2 XYZ states
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * ===========================================================================
 */
public class LeetCode1411 {
	class Solution {
	    int MOD = 1000000007;
	    public int numOfWays(int n) {
	        long xyx = 6, xyz = 6;
	        for (int i = 1; i < n; i++){
	            long nextXYX = xyx*3 + xyz*2;
	            long nextXYZ = xyx*2 + xyz*2;
	            xyx = nextXYX % MOD;
	            xyz = nextXYZ % MOD;
	        }
	        return (int)((xyx+xyz)%MOD);
	    }
	}
}
