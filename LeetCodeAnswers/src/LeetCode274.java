/* author@ Qian Cai
 * Title@ H-index
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * time: O(nlogn) sorting
 */
import java.util.*;
public class LeetCode274 {
	class Solution {
	    /*
	    [3,0,6,1,5]
	   sorting 
	            [0,   1,   3,    5,      6]
	   left      | 
	   right                              |
	   mi                   |
	   
	            [0,   1,   3,    5,      6]
	   left                | 
	   right          |
	   mi        
	    */
	    public int hIndex(int[] citations) {
	        if (citations == null || citations.length == 0) return 0;
	        Arrays.sort(citations);
	        int n = citations.length;
	        int left = 0, right = n-1;
	        while (left <= right){
	            int mi = left + (right-left)/2;
	            if (citations[mi] < n-mi) left = mi+1;
	            else right = mi-1;
	        }
	        return n-left;
	    }
	}
}
