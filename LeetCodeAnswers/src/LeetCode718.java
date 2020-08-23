/* author@ Qian Cai
 * Title@ Maximum Length of Repeated Subarray
 * Given two integer arrays A and B, return the maximum length of an subarray 
 * that appears in both arrays.
 * 
 */
public class LeetCode718 {
	public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        int res = 0;
        for (int i = m-1; i >= 0; i--){
            for (int j = n-1; j >= 0; j--){
                if (A[i] == B[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                } 
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
