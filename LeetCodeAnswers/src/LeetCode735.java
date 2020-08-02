/* author@ Qian Cai
 * Title@ Asteroid Collision
 * time, space: O(n)
 */
import java.util.*;
public class LeetCode735 {
	 public int[] asteroidCollision(int[] asteroids) {
	        Stack<Integer> stack = new Stack();
	        for (int a: asteroids){
	            if (a < 0){
	                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(a)){
	                    stack.pop();
	                }
	                if (!stack.isEmpty() && stack.peek() == -a){
	                    stack.pop();
	                    continue;
	                }
	                if (stack.isEmpty() || stack.peek() < 0) stack.push(a);
	            } else {
	                stack.push(a);
	            }
	        }
	        int n = stack.size();
	        int[] res = new int[n];
	        for (int i = n - 1; i >= 0; i--) res[i] = stack.pop();
	        return res;
	    }
}
