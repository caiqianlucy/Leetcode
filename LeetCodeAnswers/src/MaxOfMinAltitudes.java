/* author@ Qian Cai
 * Title@ Max Of Min Altitudes
 * Given a matrix with r rows and c columns, 
 * find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1].
 *  The score of a path is the minimum value in that path. For example, 
 *  the score of the path 8 → 4 → 5 → 9 is 4.
 *  
 * *** Don't include the first or final entry. You can only move either down or 
right at any point in time.
 * 
 * Solution
 * Typical dp problem,  dp[i][j] represents the maximum score 
 * go through position (i,j)
 * dp[i][j] = Math.mix(Math.max(dp[i-1][j], dp[i][j-1])), arr[i][j])
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 */
public class MaxOfMinAltitudes {
     public static int getMaxOfMin(int[][] arr) {
    	 if (arr == null || arr.length ==0 || arr[0] == null || arr[0].length == 0) return 0;
    	 int m = arr.length;
    	 int n = arr[0].length;
    	 int[][] dp = new int[m][n];
    	 dp[0][0] = Integer.MAX_VALUE;
    	 arr[m-1][n-1] = Integer.MAX_VALUE; //do not include first and final entry
    	 for (int i = 0; i < m; i++) {
    		 for (int j = 0; j < n; j++) {
    			 if (i == 0 && j == 0) continue;
    			 dp[i][j] = Math.min(Math.max((i == 0 ? Integer.MAX_VALUE: dp[i-1][j]),
    					(j == 0 ? Integer.MAX_VALUE: dp[i][j-1])), arr[i][j]);
    			 //System.out.println("i is " + i + " j is " + j + " dp is " + dp[i][j]);
    		 }
    	 }
    	 return dp[m-1][n-1];
     }
     public static void main(String[] args) {
    	 int[][] arr1 = new int[][] {{5, 1}, {4, 5}};
    	 int[][] arr2 = new int[][] {{1, 2, 3}, {4, 5, 1}};
    	 //System.out.println(getMaxOfMin(arr1));
    	 //System.out.println(getMaxOfMin(arr2));
    	 if (getMaxOfMin(arr1) == 4 && getMaxOfMin(arr2) == 4) {
    		 System.out.println("All test cases passed! Congratulations!");
    	 } else {
    		 System.out.println("There are test cases not passed!");
    	 }
     }
}
