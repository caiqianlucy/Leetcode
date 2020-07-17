/* author@ Qian Cai
 * Title@ Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * =========================================
 * Solution1: time: O(mn^2), space: O(n)
 * Solution2: time: O(mn), space: O(n)
 */
import java.util.*;
public class LeetCode85 {
	class Solution1 {
	    public int maximalRectangle(char[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
	        int m = matrix.length, n = matrix[0].length;
	        int[] prev = new int[n]; //dp[j] is the maximum height in the previous row at col j
	        int res = 0;
	        for (int i = 0; i < m; i++){
	            int[] cur = new int[n];
	            for (int j = 0; j < n; j++){
	                 cur[j] = matrix[i][j] == '0' ? 0: prev[j] +  1;              
	            }
	            prev = cur;
	            res = Math.max(res, helper(cur));
	        }
	        return res;
	    }

	    
	    public int helper(int[] heights){
	        int res = 0;
	        int n = heights.length;
	        for (int i = 0; i < n; i++){
	           int left = i, right = i;
	           while (left >= 0 && heights[left] >= heights[i]) left--;
	           while (right < n && heights[right] >= heights[i]) right++;
	           res = Math.max(res, (right-left-1)*heights[i]); 
	        }
	        
	        return res;
	    }
	}
	class Solution2 {
	    public int maximalRectangle(char[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
	        int m = matrix.length, n = matrix[0].length;
	        int[] prev = new int[n]; //dp[j] is the maximum height in the previous row at col j
	        int res = 0;
	        for (int i = 0; i < m; i++){
	            int[] cur = new int[n];
	            for (int j = 0; j < n; j++){
	                 cur[j] = matrix[i][j] == '0' ? 0: prev[j] +  1;              
	            }
	            prev = cur;
	            res = Math.max(res, helper(cur));
	        }
	        return res;
	    }
	    /*
	    
	                    [2,          5,            3]
	     idx             0           1             2  
	     stack      -1   0     
	     res                       (5*(2-0-1)) 
	                                               |
	     
	     stack      -1   0                         2
	    */
	    
	    public int helper(int[] heights){
	        int res = 0;
	        int n = heights.length;
	        Stack<Integer> stack = new Stack(); //monotonic increasing
	        stack.push(-1); //boundary index
	        for (int i = 0; i < n; i++){
	            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]){
	                res = Math.max(res, heights[stack.pop()]*(i-stack.peek()-1));
	            }
	            stack.push(i);
	        }
	        while (stack.peek() != -1){
	            res = Math.max(res, heights[stack.pop()]* (n-1-stack.peek()));
	        }
	        return res;
	    }
	}	
}
