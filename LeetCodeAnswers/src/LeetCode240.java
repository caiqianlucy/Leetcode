/* author@ Qian Cai
 * Title@ Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 *  This matrix has the following properties:
 *  Integers in each row are sorted in ascending from left to right.
 *  Integers in each column are sorted in ascending from top to bottom.
 * 
 * Solution
 * Because the rows and columns of the matrix are sorted 
 * (from left-to-right and top-to-bottom, respectively), 
 * we can prune O(m) or O(n) elements when looking at any particular value.
 * Time complexity: O(n+m)
 * Space complexity: O(1)
 */
public class LeetCode240 {
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null ||matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n-1;
        while (r < m && c >= 0){
            int cur = matrix[r][c];
            if (cur == target) return true;
            else if (cur > target){
                c--;
            } else r++;
        }
        return false;
    }
}
