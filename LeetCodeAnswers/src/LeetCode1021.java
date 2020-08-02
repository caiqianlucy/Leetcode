/* author@ Qian Cai
 * Title@ Remove Outermost Parentheses
 * 
 * 
 */
public class LeetCode1021 {
	public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0;
        int bal = 0;
        while (right < S.length()){
            char c = S.charAt(right++);
            if (c == '('){
                bal++;
            } else bal--;
            if (bal == 0){
                sb.append(S.substring(left+1, right-1));
                left = right;
            }
        }
        return sb.toString();
    }
}
