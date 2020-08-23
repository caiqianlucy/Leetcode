/* author@ Qian Cai
 * Title@ Super Palindrome
 * Let's say a positive integer is a superpalindrome if it is a palindrome, 
 * and it is also the square of a palindrome.

Now, given two positive integers L and R (represented as strings), 
return the number of superpalindromes in the inclusive range [L, R].
 * 
 */
public class LeetCode906 {
	 public int superpalindromesInRange(String L, String R) {
	        long lo = Long.valueOf(L);
	        long hi = Long.valueOf(R);
	        int limit = 100000;
	        int ans = 0;
	        //count odd length eg.121
	        for (int k = 1; k < limit; k++){
	            StringBuilder sb = new StringBuilder(Integer.toString(k));
	            for (int i = sb.length()-2; i >= 0; i--){
	                sb.append(sb.charAt(i));
	            }
	            long v = Long.valueOf(sb.toString());
	            v *= v;
	            if (v > hi) break;
	            if (v >= lo && isPalindrome(v)) ans++;
	        }
	        for (int k = 1; k < limit; k++){
	            StringBuilder sb = new StringBuilder(Integer.toString(k));
	            for (int i = sb.length()-1; i>=0; i--){
	                sb.append(sb.charAt(i));
	            }
	            long v = Long.valueOf(sb.toString());
	            v*=v;
	            if (v > hi) break;
	            if ( v >= lo && isPalindrome(v)) ans++;
	        }
	        return ans;            
	    }
	    public boolean isPalindrome(long v){
	        String s = Long.toString(v);
	        int i = 0, j = s.length()-1;
	        while (i < j){
	            if (s.charAt(i++) != s.charAt(j--)) return false;
	        }
	        return true;
	    }
}
