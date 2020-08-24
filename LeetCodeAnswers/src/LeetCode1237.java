/* author@ Qian Cai
 * Title@ Find Positive Integer Solution for a Given Equation
 * Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.

The function is constantly increasing, i.e.:

f(x, y) < f(x + 1, y)
f(x, y) < f(x, y + 1)
The function interface is defined like this: 

interface CustomFunction {
public:
  // Returns positive integer f(x, y) for any given positive integer x and y.
  int f(int x, int y);
};
For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent one function from an secret internal list, on the examples you'll know only two functions from the list.  

You may return the solutions in any order.
Constraints:

1 <= function_id <= 9
1 <= z <= 100
It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000
 */
import java.util.*;
public class LeetCode1237 {
	 public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
	        List<List<Integer>> res = new ArrayList();
	        for (int i = 1; i <= 1000; i++){
	            int left = 1;
	            int right = 1000;
	            while (left <= right){
	                int mi = (left + right)/2;
	                int val = customfunction.f(i, mi);
	                if ( val == z) {
	                    res.add(Arrays.asList(i, mi));
	                    break;
	                }
	                else if (val < z) left = mi+1;
	                else right = mi-1;
	            }
	        }
	        return res;
	    }
	 class CustomFunction {
		      // Returns f(x, y) for any given positive integers x and y.
		      // Note that f(x, y) is increasing with respect to both x and y.
		      // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
		      public int f(int x, int y);
		  };
}
