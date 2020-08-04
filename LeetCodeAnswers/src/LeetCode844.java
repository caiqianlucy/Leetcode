/* author@ Qian Cai
 * Title@ Backspace String Compare
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Note that after backspacing an empty text, the text will continue empty.
 * =============================
 * Solution1： time: O(m+n) space: O(m+n)
 * Solution2: time: O(m+n) space: O(1)
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
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            int i = S.length()-1, j = T.length()-1;
            int skipS = 0, skipT = 0;
            while (i >= 0 || j >= 0){           
               i = process(S, i, skipS);         
               j = process(T, j, skipT);
                if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
                if ((i >= 0) != (j >= 0)) return false;
                i--;
                j--;
            }
            return true;
        }
        public int process(String S, int i, int skipS){
            //System.out.println(i);
            while (i >= 0){
                    if (S.charAt(i) == '#'){
                        skipS++;
                        i--;
                    } else if (skipS > 0){
                        skipS--;
                        i--;
                    } else break;
            }
           // System.out.println(i);
            return i;
            
        }
    }
}
