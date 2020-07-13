/* author@ Qian Cai
 * Title@ Minimum Domino Rotations For Equal Row
 * 
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.


 * time: O(n) 
 */
public class LeetCode1007 {
	 public int minDominoRotations(int[] A, int[] B) {
	        int one = check(A[0], A, B);
	        int two = check(B[0], A, B);
	        if (one == -1 && two == -1) return -1;
	        if (one == -1) return two;
	        if (two == -1) return one;
	        return Math.min(check(A[0], A, B), check(B[0], A, B));
	    }
	    public int check(int v, int[] A, int[] B){
	        int acount = 0, bcount = 0;
	        for (int i = 0; i < A.length; i++){
	            if (A[i] != v && B[i] != v) return -1;
	            if (A[i] != v) acount++;
	            else if (B[i] != v) bcount++;
	        }
	        return Math.min(acount, bcount);
	    }
}
