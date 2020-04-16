/*author@ Qian Cai
 * Title@ Palindrome Number
 * Determine whether an integer is a palindrome. An integer is a palindrome 
 * when it reads the same backward as forward.
 * 
 */
public class LeetCode9 {
	public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        int i = 0, j = s.length()-1;
        while (i < j){
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
	//without converting x to string
	public boolean isPalindrome2(int x) {
		if (x < 0 || (x % 10 == 0 && x != 0)) return false;
		int revertNumber = 0;
		while (x > revertNumber) {
			revertNumber = revertNumber*10 + x%10;
			x/=10;
		}
		return x == revertNumber || x == revertNumber/10;
	}
}
