/* author@ Qian Cai
 * Title@ Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
 * 
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
public class LeetCode20 {
	public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack();
        for (char c: s.toCharArray()){
            if (map.containsKey(c)){
                if (stack.isEmpty() || stack.pop() != map.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
