/* author@ Qian Cai
 * Title@ Parse Lisp Expression
 * time: O(n^2)
 * 
 */
import java.util.*;
public class LeetCode736 {
	public int evaluate(String expression) {
        scope.add(new HashMap()); // add a new level for the current expression
        int ans = evaluate_inner(expression);
        scope.remove(scope.size()-1); //remove the current level when finished evaluation for the expression
        return ans;
    }
    ArrayList<Map<String, Integer>> scope; //always look variable from the latest scope
    public LeetCode736(){
        scope = new ArrayList();
        scope.add(new HashMap());
    }
    public int evaluate_inner(String expression){
        if (expression.charAt(0) != '('){
            if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-'){
                return Integer.parseInt(expression);
            }
            //if is a variable directly return its value
            for (int i = scope.size()-1; i>=0; i--){
                if (scope.get(i).containsKey(expression)) return scope.get(i).get(expression);
            }
            
        }
        //get tokens after operation
        List<String> tokens = parse(expression.substring(expression.charAt(1) == 'm' ? 6: 5, expression.length()-1));
        if (expression.startsWith("add", 1)){
            return evaluate(tokens.get(0)) + evaluate(tokens.get(1));
        } else if (expression.startsWith("mult", 1)){
            return evaluate(tokens.get(0))*evaluate(tokens.get(1));
        } else{
            //startsWith "let"
            for (int j = 1; j < tokens.size(); j+=2){
                scope.get(scope.size()-1).put(tokens.get(j-1), evaluate(tokens.get(j)));
            }
            return evaluate(tokens.get(tokens.size()-1));
        }           
    }
    //level by level parse
    public List<String> parse(String expression){
        List<String> ans = new ArrayList();
        int bal = 0;
        StringBuilder buf = new StringBuilder();
        for (String token: expression.split(" ")){
            for (char c: token.toCharArray()){
                if (c == '(') bal++;
                else if (c == ')') bal--;
            }
            if (buf.length() > 0) buf.append(" ");
            buf.append(token);
            if (bal == 0){
                ans.add(buf.toString());
                buf = new StringBuilder();
            }
        }
        if (buf.length() > 0) ans.add(new String(buf));
        return ans;
    }
}
