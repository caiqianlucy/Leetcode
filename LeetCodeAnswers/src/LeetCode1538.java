/* author@ Qian Cai
 * Title@ Guess the Majority in a Hidden array
 * We have an integer array nums, where all the integers in nums are 0 or 1. You will not be given direct access to the array, instead, you will have an API ArrayReader which have the following functions:

int query(int a, int b, int c, int d): where 0 <= a < b < c < d < ArrayReader.length(). The function returns the distribution of the value of the 4 elements and returns:
4 : if the values of the 4 elements are the same (0 or 1).
2 : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
0 : if two element have a value equal to 0 and two elements have a value equal to 1.
int length(): Returns the size of the array.
 */
public class LeetCode1538 {
	//time: O(n)
	class Solution {
	    public int guessMajority(ArrayReader reader) {
	        int n = reader.length();
	        int first = reader.query(0, 1, 2, 3);
	        int groupA = 1, groupB = 0;
	        int idxA = 3, idxB = -1;
	        for (int i = 4; i < n; i++){
	            //same number as nums[3]
	            if (reader.query(0, 1, 2, i) == first){
	                groupA++;
	            } else {
	                groupB++;
	                idxB = i;
	            }
	        }
	        int second = reader.query(0, 1, 2, 4);
	        if (reader.query(1, 2, 3, 4) == second){
	            groupA++;
	        } else{
	            groupB++;
	            idxB = 0;
	        }
	        if (reader.query(0, 2, 3, 4) == second){
	            groupA++;
	        } else {
	            groupB++;
	            idxB = 1;
	        }
	        if (reader.query(0, 1, 3, 4) == second){
	            groupA++;
	        } else {
	            groupB++;
	            idxB = 2;
	        }
	        if (groupA > groupB) return idxA;
	        if (groupB > groupA) return idxB;
	        return -1;
	    }
	   
	     // This is the ArrayReader's API interface.
	      // You should not implement it, or speculate about its implementation
	      interface ArrayReader {
	        public:
	          // Compares 4 different elements in the array
	          // return 4 if the values of the 4 elements are the same (0 or 1).
	          // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
	          // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
	          public int query(int a, int b, int c, int d);
	     
	          // Returns the length of the array
	          public int length();
	     };
	     
}
