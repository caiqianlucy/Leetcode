/* author@ Qian Cai
 * Title@ Diagonal Traverse
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in 
 * diagonal order as shown in the below image.
 * 
 */
public class LeetCode498 {
	 /*
    [             |
      [ 1,   2,   3 ],
      [ 4,   5,   6 ],
      [ 7,   8  , 9 ]
        |
    ]
    constant i + j = step
    */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m*n];
        int idx = 0;
        int iStart = 0, iEnd = 0, dev = 0;
        for (int step = 0; step < m+n-1; step++){
            if (step % 2 == 0){
                iStart = Math.min(step, m-1);
                iEnd = Math.max(0, step-n+1);
                dev = -1;
            } else {
                iStart = Math.max(step-n+1, 0);
                iEnd = Math.min(step, m-1);
                dev = 1;
            }
            for (int i = iStart; (dev > 0 ? i <= iEnd: i >= iEnd); i+= dev){
                res[idx++] = matrix[i][step-i];
            }
        }
        return res;
    }
    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m*n];
        int idx = 0;
        int di= -1, dj = 1;
        int i = 0, j = 0; // index of row and col in matrix
        while (idx < m*n){
            res[idx++] = matrix[i][j];   
            if (i + di >= 0 && i + di < m && j+dj >= 0 && j+dj < n){
                i += di;
                j += dj;
            } else {
                if (i + di >= m){
                j++;
                } else if (j + dj >= n){
                i++;
                } else if (i + di < 0){
                j++;
                } else if (j + dj < 0){
                i++;
                } 
                di *= -1;
                dj *= -1;
            }
        }
        return res;
    }
}
