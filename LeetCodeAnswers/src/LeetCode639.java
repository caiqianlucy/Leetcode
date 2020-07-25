/* author@ Qian Cai
 * Title@ Decode Ways II
 * 
 * 
 */
public class LeetCode639 {
	int MOD = (int)Math.pow(10, 9) + 7;
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        long prev_two  = 1; // empty string base as 1
        long prev_one = count(s.substring(0, 1));
        if (s.length() == 1) return (int)count(s);
        for (int i = 1; i < s.length(); i++){
            long cur = 0;
            long one = count(s.substring(i, i+1));
            cur += (one*prev_one % MOD);
            cur %= MOD;
            long two = count(s.substring(i-1, i+1));
            cur += (two*prev_two % MOD);
            cur %= MOD;
            prev_two = prev_one;
            prev_one = cur;
        }
        return (int)prev_one;
    }
    public long count(String s){
        if (s.length() == 1){
            if (s.charAt(0) == '*') return 9;
            else if (s.charAt(0) != '0') return 1;
            return 0;
        }
        if (s.length() == 2){
            if (s.charAt(0) != '*' && s.charAt(1) != '*'){
                int val = Integer.valueOf(s);
                if (val >= 10 && val <= 26) return 1;
                else return 0;
            } else if (s.charAt(0) == '*' && s.charAt(1) == '*'){
                return 15; //11-19, 21-26
            } else if (s.charAt(0) == '*'){
                return (s.charAt(1)-'0') <= 6 ? 2: 1;
            } else {
                return s.charAt(0) == '0' ? 0 : s.charAt(0) == '1'? 9: s.charAt(0) == '2' ? 6: 0;
            }
        }
        return 0;
    }
}
