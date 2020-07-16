/* author@ Qian Cai
 * Title@ Basic Calculator
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .
 *  
 */
import java.util.*;

public class LeetCode224 {
	public int calculate(String s) {
        Deque<Character> deque = new LinkedList();
        for (char c: s.toCharArray()){
            deque.addLast(c);
        }
        return evaluate(deque);
    }
    public int evaluate(Deque<Character> deque){
        int sign = 1;
        int num = 0;
        int res = 0;
        while (!deque.isEmpty()){
            char c = deque.pollFirst();
            if (Character.isDigit(c)){
                num = num*10 + (c-'0');
            } else if (c == '+' || c == '-' || c == ' '){
                res += num*sign;
                num = 0;
                if (c == '+') sign = 1;
                else if (c == '-') sign = -1;
            } else if ( c== '('){
                res += sign*evaluate(deque);
                num = 0;
            } else{
                break;
            }
        }
        res += sign*num;
        return res;
    }
}
