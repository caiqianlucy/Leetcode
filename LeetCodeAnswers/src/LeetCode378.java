/*author@Qian Cai
 * Title@ Kth Smallest Element in a Sorted Matrix
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * ========================================================================
 * Solution
 * time: O(nlog(max-min))
 * Space: O(1)
 * 
 */
public class LeetCode378 {
	public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n-1][n-1];
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            if (count(matrix, mi) < k){
                lo = mi+1;
            } else hi = mi;
        }
        return lo;
    }
    //count of numbers <= target
    public int count(int[][] matrix, int target){
        int i = 0, j = matrix.length-1;
        int res = 0;
        while (i < matrix.length && j >= 0){
            if (matrix[i][j] <= target){
                res += j+1;
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
