/*author@ Qian Cai
 * Title@ Optimal Utilization
 * Given 2 lists a and b. Each element is a pair of integers where the first integer 
 * represents the unique id and the second integer represents a value. Your task is to
 *  find an element from a and an element form b such that the sum of their values is 
 *  less or equal to target and as close to target as possible. Return a list of ids of
 *  selected elements. 
 * If no pair is possible, return an empty list.
 * Time: O(mlogm+nlogn) Space:
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OptimalUtilization {
     public List<int[]> getPairs(List<int[]> a, List<int[]> b, int target){
    	 Collections.sort(a, (i, j)->(i[1]-j[1]));
    	 Collections.sort(b, (i, j)->(i[1]-j[1]));
    	 List<int[]> res = new ArrayList();
    	 int temp = Integer.MIN_VALUE;
    	 int m = a.size();
    	 int n = b.size();
    	 int i = 0, j = n-1;
    	 while (i < m && j >= 0) {
    		 int sum = a.get(i)[1] + b.get(j)[1];
    		 if (sum > target) {
    			 j--;
    		 } else {
    			 if (sum > temp) {
    				 res.clear();
    				 temp = sum;
    				 res.add(new int[] {a.get(i)[0], b.get(j)[0]});
    			 } else if (sum == temp) {
    				 res.add(new int[] {a.get(i)[0], b.get(j)[0]});
    				 int idx = j-1;
    				 while (idx >= 0 && b.get(idx)[1] == b.get(j)[1]) {
    					 res.add(new int[] {a.get(i)[0], b.get(idx--)[0]});
    				 }
    			 }
    			 i++;
    		 }
    	 }
    	 return res;
     }
}
