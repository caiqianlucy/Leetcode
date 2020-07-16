/* author@ Qian Cai
 * Title@ Backspace String Compare
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Note that after backspacing an empty text, the text will continue empty.
 * 
 */
import java.util.Stack;
public class LeetCode844 {
	public boolean backspaceCompare(String S, String T) {
        String ps = process(S);
        String pt = process(T);
        return ps.equals(pt);
    }
    public String process(String s){
        Stack<Character> stack = new Stack();
        for (char c: s.toCharArray()){
            if (c=='#'){
                if (!stack.isEmpty()) stack.pop();
            } else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }
}
