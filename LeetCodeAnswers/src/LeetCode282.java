/* author@ Qian Cai
 * Title@ Expression Add Operators
 * time: O(4^n) space: O(n)
 * 
 */
import java.util.*;
public class LeetCode282 {
	public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        if (num.length()  == 0) return res;
        dfs(num, 1, num.substring(0, 1), target, res);
        return res;    
    }
    public void dfs(String num, int i, String s, long target, List<String> res){
        if (i == num.length()){
            if (eval(s) == target){
                res.add(s);
            }
            return;
        }
            dfs(num, i+1, s + "*" + num.charAt(i), target, res);
            dfs(num, i+1, s + "+" + num.charAt(i), target, res);
            dfs(num, i+1, s + "-" + num.charAt(i), target, res);
             
        //avoid "1*05" case, leading 0 in front
        if (check(s)) dfs(num, i+1, s + num.charAt(i), target, res);
    }
    public boolean check(String s){
        int n = s.length();
        if (s.charAt(n-1) == '0' && (n == 1 || !Character.isDigit(s.charAt(n-2)))) return false;
        return true;
    }
    public long eval(String s){
        //System.out.println(s);
        long res = 0;      //accumutive final res
        long sign = 1;    //last +/- operation
        long last = 1;   //running product for multiply
        long num = 0;   //current number
        for (char c: s.toCharArray()){
            if (Character.isDigit(c)){
                num = num*10 + (c-'0');
            } else if (c == '+' || c == '-'){
                res += last*num*sign;
                last = 1;
                num = 0;
                sign = (c == '+') ? 1: -1;
            } else if (c == '*'){
                last *= num;
                num = 0;
            }
        }
        res += last*num*sign;
        //System.out.println(res);
        return res;
    }
}
