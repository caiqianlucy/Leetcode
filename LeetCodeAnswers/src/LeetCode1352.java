/* author@ Qian Cai
 * Title@ Product of the last K Numbers
 * Implement the class ProductOfNumbers that supports two methods:

1. add(int num)

Adds the number num to the back of the current list of numbers.
2. getProduct(int k)

Returns the product of the last k numbers in the current list.
You can assume that always the current list has at least k numbers.
At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 * time: O(1)
 * space: O(n)
 */
import java.util.*;
public class LeetCode1352 {
	class ProductOfNumbers {
	    List<Integer> list; //accumutive product 
	    public ProductOfNumbers() {
	        list = new ArrayList();
	    }
	    
	    public void add(int num) {
	        //handle corner case for zero
	        if (num == 0){
	            list  = new ArrayList();
	        } else {
	            if (list.isEmpty()){
	               list.add(num);
	            } else {
	                list.add(num*list.get(list.size()-1));
	            }
	        }
	    }
	    
	    public int getProduct(int k) {
	        if (list.size() < k) return 0; //there is 0 in the last k element
	        if (list.size() == k) return list.get(list.size()-1);
	        return list.get(list.size()-1)/list.get(list.size()-k-1);
	        
	    }
	}
}
