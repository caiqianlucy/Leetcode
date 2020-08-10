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
    //without scope
    class Solution {
        public int evaluate(String expression) {
            return helper(expression, new HashMap());
    	}
    	public int helper(String expression, Map<String, Integer> map){
    		Map<String, Integer> copy = new HashMap();
    		for (String key: map.keySet()) copy.put(new String(key), map.get(key));
            char c = expression.charAt(0);
    		if (c != '('){
    			if (Character.isDigit(c) || c == '-'){
    				return Integer.parseInt(expression);
    			}
    			//variable 
    			return map.get(expression);
    		} 
    		List<String> tokens = parse(expression.substring(expression.charAt(1) == 'm'  ? 6: 5, expression.length()-1));
    		if (expression.startsWith("add", 1)){
    			return helper(tokens.get(0), copy) + helper(tokens.get(1), copy);
    		} else if (expression.startsWith("mult", 1)){
    			return helper(tokens.get(0), copy)*helper( tokens.get(1), copy);
    		} else {
    			for (int j = 1; j < tokens.size(); j+= 2){
    				copy.put(tokens.get(j-1), helper(tokens.get(j), copy));
    			}
    			return helper(tokens.get(tokens.size()-1), copy);
    		}
    		
    	}
               public List<String> parse(String expression){
    		List<String> ans = new ArrayList();
    		int bal = 0;
    		StringBuilder buf = new StringBuilder();
    		for (String s: expression.split(" ")){
    			for (char c: s.toCharArray()){
    				if (c == '(') bal++;
    				else if (c == ')') bal--;
    			}
    			if (buf.length() > 0) buf.append(" ");
    			buf.append(s);
    			if (bal == 0){
    				ans.add(buf.toString());
    				buf = new StringBuilder();
    			}
    		}
    		if (buf.length() > 0) ans.add(buf.toString());
    		return ans;
    	}   
    }
    //modulized solution
    class Solution2 {
        public int evaluate(String expression) {
            return evaluate(expression, new HashMap());
        }

        private int evaluate(String e, Map<String, Integer> map) {
        char c = e.charAt(1);          // the expression must start with "(add " or "(mult " or "(let ". 
            
        if (c == 'a') {                // "add" expression
            return evaluateAdd(e, map);
        } else if (c == 'm') {         // "mult" expression
            return evaluateMult(e, map);
        } else if (c == 'l') {         // "let" expression
            return evaluateLet(e, map);
        } else {
            return 0;                  // illegal expression so return 0
        }
    }
        
    private int evaluateAdd(String e, Map<String, Integer> map) {
        int offset = 5;      // the expression starts with "(add ", so offset starts at 5.
        String o1 = getOperand(e, offset);  // 1st operand
            
        offset += o1.length() + 1;
        String o2 = getOperand(e, offset);  // 2nd operand
            
        return evaluateOperand(o1, map) + evaluateOperand(o2, map);
    }
        
    private int evaluateMult(String e, Map<String, Integer> map) {
        int offset = 6;      // the expression starts with "(mult ", so offset starts at 6.
        String o1 = getOperand(e, offset);  // 1st operand
            
        offset += o1.length() + 1;
        String o2 = getOperand(e, offset);  // 2nd operand
            
        return evaluateOperand(o1, map) * evaluateOperand(o2, map);
    }
        
    private int evaluateLet(String e, Map<String, Integer> map) {
        Map<String, Integer> copy = new HashMap();
        for (String key: map.keySet()) copy.put(key, map.get(key));
        int res = 0;     // the result of this "let" expression
        int offset = 5;  // the expression starts with "(let ", so offset starts at 5.
            
        while (offset < e.length()) {
            String o1 = getOperand(e, offset);  // 1st operand
            offset += o1.length() + 1;
                
            String o2 = getOperand(e, offset);  // 2nd operand
                
            if (o2 == null) {
                res = evaluateOperand(o1, copy); // if 2nd operand is null, we reached the last operand
                break;
            }
                
            offset += o2.length() + 1;

           copy.put(o1, evaluateOperand(o2, copy));  // do the assignment
        }    
        return res;
    }
        
    private String getOperand(String e, int offset) {
        if (offset >= e.length()) return null;  // invalid offset
        
        char c = e.charAt(offset);
        int start = offset;
            
        if (c == '-' || Character.isDigit(c)) {  // operand is an integer
            if (c == '-') offset++;
            while (offset < e.length() && Character.isDigit(e.charAt(offset))) offset++;
                
        } else if (Character.isLowerCase(c)) {  // operand is a variable
            while (offset < e.length() && Character.isLetterOrDigit(e.charAt(offset))) offset++;
                
        } else {                                // operand is another expression enclosed in parentheses
            for (int cnt = 0; offset < e.length();) {
                c = e.charAt(offset++);
                if (c == '(') cnt++;
                if (c == ')') cnt--;
                if (cnt == 0) break;
            }
        }
            
        return e.substring(start, offset);
    }
        
    private int evaluateOperand(String e, Map<String, Integer> map) {
        char c = e.charAt(0);
            
        if (c == '-' || Character.isDigit(c)) {  // operand is an integer so parse it
            return Integer.parseInt(e);
                
        } else if (Character.isLowerCase(c)) {  // operand is a variable so look it up
            //System.out.println(e);
            return map.get(e);
                
        } else {                               // operand is another expression so evaluate it recursively
            return evaluate(e, map);
        }
    }
    }
}
