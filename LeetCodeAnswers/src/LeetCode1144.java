/* author@ Qian Cai
 * Title@ Decrease Elements To Make Array ZigZag
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.

An array A is a zigzag array if either:

Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
Return the minimum number of moves to transform the given array nums into a zigzag array.
 */
public class LeetCode1144 {
	public int movesToMakeZigzag(int[] nums) {
        int pattern1 = 0, pattern2 = 0; //pattern 1, even-idx element greater, pattern 2, even-idx element smaller
        int[] temp1 = nums.clone();
        for (int i = 1; i < temp1.length; i++){
            if (i % 2 == 0){
                if (temp1[i] <= temp1[i-1]){
                    pattern1 += temp1[i-1]-temp1[i]+1;
                } 
            } else {
                if (temp1[i] >= temp1[i-1]){
                    pattern1 += temp1[i] - temp1[i-1] + 1;
                    temp1[i] = temp1[i-1]-1;
                }
            }            
        }
        int[] temp2 = nums.clone();
        for (int i = 1; i < temp2.length; i++){
            if (i % 2 == 0){
                if (temp2[i] >= temp2[i-1]){
                    pattern2 += temp2[i]-temp2[i-1] + 1;
                    temp2[i] = temp2[i-1] - 1;
                } 
            } else {
                if (temp2[i] <= temp2[i-1]){
                    pattern2 += temp2[i-1] - temp2[i] + 1;
                }
            }
        }
        return Math.min(pattern1, pattern2);
        
    }
}
