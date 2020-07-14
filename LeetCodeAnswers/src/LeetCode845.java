/* author@ Qian Cai
 * Title @ Longest Mountain in Array
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.
 * =====================================================
 * Solution 1: dynamic programming 
 * time: O(n) space: O(1)
 * Solution 2: Two Pointer
 * time: O(n), space: O(1)
 * 
 */
public class LeetCode845 {
	//time O(n) space: O(1)
    public int longestMountain1(int[] A) {
        int hill = 0, bottom = 0; // the max length for last element to be either hill or bottom
        if (A.length < 3) return 0;
        int res = 0;
        for (int i = 1; i < A.length; i++){
            int newHill, newBottom;
            if (A[i] > A[i-1]){
                newHill = (hill == 0 ? 1: hill) +1;
                newBottom = 0;
            } else if (A[i] == A[i-1]){
                newHill = 0;
                newBottom = 0;
            } else {
                newBottom = Math.max(((bottom == 0) ? 0: bottom + 1), ((hill == 0 ? 0: hill + 1)));
                newHill = 0;
            }
            bottom = newBottom;
            hill = newHill;
            res = Math.max(bottom, res);
        }
        return res;
        
    }
    public int longestMountain2(int[] A) {
        int n = A.length;
        int res = 0; 
        int i = 0;
        while (i < n){
            int j = i;
            //i is the left boundary of a mountain
            if (j + 1 < n && A[j] < A[j+1]){
                 while (j + 1 < n && A[j+1] > A[j]){
                j++;
                }
               if (j+1 < n && A[j] > A[j+1]){
                    while (j+1 < n && A[j] > A[j+1]) j++;
                    res = Math.max(res, j-i+1);
                }
            }
            //move i to look for next mountain
            i = Math.max(j, i+1);
        }
        return res;
    }
}
