/* author@ Qian Cai
 * Title@ Add to Array-Form of Integer
 * 
 */
import java.util.*;
public class LeetCode989 {
	 public List<Integer> addToArrayForm(int[] A, int K) {
	        List<Integer> res = new ArrayList();
	        int carry = K;
	        int i = A.length-1;
	        while (i >= 0 || carry > 0){
	            carry += ((i >= 0) ? A[i--]:0);
	            //System.out.println(sum);
	            
	            res.add(0, carry%10);
	            carry /= 10;
	        }
	        return res;
	        
	    }
}
