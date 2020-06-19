/* author@ Qian cai
 * Mock Haoshan 20200618
 * problem: given an array of size n, choose a number from either side and repeat this step to form a new array, then maximize the product of the new array and [1, 2, 3, …., n]

 * 
 */
public class MaxSumPop {
  public static int maxSumPop(int[] arr) {
	  int n = arr.length;
	  int[][] dp = new int[n][n+1]; // dp[i][l] is the from i with len l after popping all the rest element
	  int res = 0;
	  for (int l = n-1; l >= 0; l--) {
		  int fac = n-l;
		  for (int i = 0; i < n-l; i++) {
			  dp[i][l] = Math.max(i == 0? 0 : dp[i-1][l+1] + fac*arr[i-1] , (i + l == n) ? 0: dp[i][l+1]+fac*arr[i+l]);
			  System.out.println("i: " + i + " l: " + l + " res: " + dp[i][l]);
			  if (l == 0) res = Math.max(res, dp[i][0]);
		  }
		  
	  }
	  return res;
  }
  public static void main(String[] args) {
	  int[] arr = new int[] {6, 3, 2, 4, 5, 1};
	  int res = maxSumPop(arr);
	  System.out.println(res);
  }
}
