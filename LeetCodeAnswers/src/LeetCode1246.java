/* author@ Qian Cai
 * Title@ Palindrom Removal
 * Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.

 * Time: O(n^3), Space: O(n^2)
 */
public class LeetCode1246 {
	int[][] dp;
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        dp = new int[n][n];
        return helper(0, n-1, arr);
    }
    private int helper(int l, int r, int[] arr){
        if (l > r) return 0;
        if (l == r) return 1;
        if (dp[l][r] != 0) return dp[l][r];
        int res  = helper(l+1, r, arr) + 1;
        if (arr[l] == arr[l+1]) res = Math.min(res, 1+helper(l+2, r, arr));
        //choose every point to check 
        for (int k = l+2; k<=r; k++){
            if (arr[l] == arr[k]) res = Math.min(res, helper(l+1, k-1, arr) + helper(k+1, r, arr));
        }
        dp[l][r] = res;
        return res;
    }
}
