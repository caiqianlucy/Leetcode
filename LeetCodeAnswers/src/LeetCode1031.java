/* author@ Qian Cai
 * Title@ Maximum Sum of Two non-overlapping subarrays
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 * 
 */
public class LeetCode1031 {
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        return Math.max(helper(A, L, M), helper(A, M, L));
    }
    public int helper(int[] A, int L, int M){
        int sum = 0;
        int lSum = 0; //sum of L elements
        for (int i = 0; i < L+M;i++){
            sum += A[i];
            if(i == L-1) lSum = sum;
        }        
        int mSum = sum-lSum;
        int lmax = lSum; //max sum of L element on the left of M
        int res = sum; //max L+M, L is on the left of M
        for (int i = L+M; i < A.length; i++){
            mSum += A[i]-A[i-M];
            lSum += A[i-M]- A[i-L-M];
            lmax = Math.max(lmax, lSum);
            res = Math.max(res, mSum + lmax);
        }
        return res;
    }
}
