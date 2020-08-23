/* author@ Qian Cai
 * Title@ Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 */
import java.util.*;
public class LeetCode150 {
	 public int evalRPN(String[] tokens) {
	        Stack<Integer> vals = new Stack();
	        for (String token: tokens){
	            if (token.equals("/") || token.equals("+") || token.equals("-") || token.equals("*")){
	                int op2 = vals.pop();
	                int op1 = vals.pop();
	                if (token.equals("/")) vals.push(op1/op2);
	                else if (token.equals("*")) vals.push(op1*op2);
	                else if (token.equals("+")) vals.push(op1 + op2);
	                else vals.push(op1-op2);
	            } else {
	                vals.push(Integer.valueOf(token));
	            }
	        }
	        return vals.pop();
	    }
}
