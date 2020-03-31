/* author@Qian Cai
 * title@Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 * 
 */
//Time: O(n) Space: O(n)
public class LeetCode1249 {
	public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0;
        for (char c: s.toCharArray()){
            if (Character.isLetter(c)) sb.append(c);
            else if (c == '('){
                left++;
                sb.append(c);
            } 
            else if (c == ')'){
                if (left > right){
                    right++;
                    sb.append(c);
                }
            }
        }
        if (left == right) return sb.toString();
        String ns = sb.toString();
        left = right = 0;
        StringBuilder res = new StringBuilder();
        for (int i = ns.length()-1; i>= 0; i--){
            char c = ns.charAt(i);
            if (Character.isLetter(c)) res.append(c);
            else if (c == ')'){
                right++;
                res.append(c);
            } 
            else if (c == '('){
                if (right > left){
                    left++;
                    res.append(c);
                }
            }
        }
        return res.reverse().toString();
    }
}
