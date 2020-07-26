/* author@ Qian Cai
 * Title@ Basic Calculator III
 * 
 * 
 */
import java.util.*;
public class LeetCode772 {
	 public int calculate(String s) {
	        Deque<Character> deque = new LinkedList();
	        for (char c: s.toCharArray()){
	            if (c != ' ') deque.add(c);
	        }
	        return helper(deque);
	    }
	    public int helper(Deque<Character> deque){
	        int last = 1; //last number fo */
	        int num = 0; //current running number
	        char op = '*'; //division and multiplication
	        int sign = 1;
	        int res = 0;
	        while (!deque.isEmpty()){
	            char c = deque.removeFirst();
	            if (Character.isDigit(c)){
	                num = num*10 + (c-'0');
	            } else if (c == '('){
	                num = helper(deque);
	            } else if (c == '+' || c == '-'){
	                res += sign * eval(last, num, op);
	                last = 1;
	                op = '*';
	                num = 0;
	                sign = (c == '+') ? 1: -1;
	            } else if (c == '*' || c == '/'){
	                last = eval(last, num, op);
	                op = c;
	                num = 0;
	                
	            } else if (c == ')'){
	               return res += sign*eval(last, num, op);            
	            }
	        }
	        return res += sign*eval(last, num, op);
	    }
	    public int eval(int a, int b, char op){
	        if (op == '*') return a*b;
	        else return a/b;
	    }
}
