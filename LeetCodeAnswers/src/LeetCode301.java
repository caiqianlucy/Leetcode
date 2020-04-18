/* author@ Qian Cai
 * Title@ Remove Invalid Parentheses
 * Remove the minimum number of invalid parentheses in order to make the input
 *  string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).
 *  Times comlexity: O(2^n)
 *  Space complexity: O(n)
 */
import java.util.*;
public class LeetCode301 {
	Set<String> res = new HashSet();
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                left++;
            } else if (s.charAt(i) == ')'){
                if (left > 0){
                    left--;
                } else right++;
            }
        }
        
        backtrack(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(res);
    }
    public void backtrack(String s, int i, int l, int r, int misL, int misR, StringBuilder expression){
        if (i == s.length()){
            if (misL == 0 && misR == 0) res.add(expression.toString());
        } else {
            char c = s.charAt(i);
            if (c == '(' && misL > 0){
                backtrack(s, i+1, l, r, misL-1, misR, expression);
            } else if (c ==')' && misR > 0){
                backtrack(s, i+1, l, r, misL, misR-1, expression);
            }
            expression.append(c);
            if (c == ')'){
                if (l > r) {
                    backtrack(s, i+1, l, r+1, misL, misR, expression);
                }
            } else{
                backtrack(s, i+1, l+ (c=='('?1:0), r, misL, misR, expression);
            }
            expression.deleteCharAt(expression.length()-1); 
        }
    }
}
