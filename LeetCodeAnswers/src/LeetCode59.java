/*author@ Qian Cai
 * Title@ Spiral Matrix II
 * Given a positive integer n, generate a square matrix filled with elements
 *  from 1 to n2 in spiral order.
 * Solution: change direction whenever hit edge or visited position.
 * Time complexity: O(n^2)
 * Space complexity: O(n^2)
 */
public class LeetCode59 {
	public int[][] generateMatrix(int n) {
        int[][] res =  new int[n][n];
        int i = 1;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0;
        
        while (i <= n*n){
            res[x][y] = i++;
            
            while (i <= n*n && (x+dirs[d][0] < 0 || x + dirs[d][0] >= n || y + dirs[d][1] < 0 || y +dirs[d][1]>= n || res[x+dirs[d][0]][y+dirs[d][1]] != 0)){
                d = (d+1)%4;   
            }
            x = x + dirs[d][0];
            y = y + dirs[d][1];
        }
        return res;
    }
}
