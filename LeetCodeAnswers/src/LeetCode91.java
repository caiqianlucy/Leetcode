/* author@ Qian Cai
 * Title@ Decode Ways
 * A message containing letters from A-Z is being encoded to numbers using the following 
 * mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to
 decode it.
 * 
 */
public class LeetCode91 {
	//time: O(n) space: O(1)
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        if (s.length() == 1) return s.charAt(0) == '0'? 0: 1;
        int prev_two = 1, prev_one = s.charAt(0) == '0'? 0: 1;
        for (int i = 1; i < s.length(); i++){
            int cur = 0;
            if (s.charAt(i) != '0') cur += prev_one;
            int two_digit_val = Integer.valueOf(s.substring(i-1, i+1));
            if (two_digit_val >= 10 && two_digit_val <= 26){
                cur+= prev_two;
            }
            prev_two = prev_one;
            prev_one = cur;
        }
        return prev_one;
        
    }
}
