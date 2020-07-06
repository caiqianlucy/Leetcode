import java.util.*;

/* author@ Qian Cai
 * Title@ Count Submatrices With All Ones
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.

 

Example 1:

Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2. 
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:

Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3. 
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2. 
There are 2 rectangles of side 3x1. 
There is 1 rectangle of side 3x2. 
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
Example 3:

Input: mat = [[1,1,1,1,1,1]]
Output: 21
Example 4:

Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5
==============================================================
Solution 1: time: O(m^2*n) space: O(n)
Solution 2: time: O(m*n) space: O(n)

 */

public class LeetCode1504 {
	public int numSubmat1(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        for (int top = 0; top < m; top++){
            int[] h = new int[n];
            Arrays.fill(h, 1);
            for (int down = top; down < m; down++){
                for (int col = 0; col < n; col++){
                    h[col] = (h[col]&mat[down][col]);                  
                }
                res += countOneDimension(h);
            }
        }
        return res;
    }
    public int countOneDimension(int[] h){
        int res = 0;
        int cur = 0;
        for (int i = 0; i < h.length; i++){
            cur = (h[i] == 0 ? 0: cur+1);
            res += cur;
        }
        return res;
    }
    public int numSubmat2(int[][] mat) {
        int m = mat.length, n = mat[0].length, height[] = new int[n], res = 0; 
        for (int i = 0; i < m; i++) {
            Stack<int[]> st = new Stack<>();
            for (int j = 0; j < n; j++) {
                height[j] = mat[i][j] == 0 ? 0 : (height[j] + 1);   // horizontal height of histogram;
                int sum = 0;
                while(!st.isEmpty() && height[st.peek()[0]] >= height[j]) st.pop();
                if (!st.isEmpty()) sum += height[j] * (j - st.peek()[0]) + st.peek()[1];
                else sum += height[j] * (j + 1);
                st.push(new int[]{j, sum});
                res += sum;
            }
        }
        return res;
    }
}
