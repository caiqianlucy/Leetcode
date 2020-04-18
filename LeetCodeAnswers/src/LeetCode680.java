/* author@ Qian Cai
 * Title@ Valid Palindrome II
 * Given a non-empty string s, you may delete at most one character. 
 * Judge whether you can make it a palindrome.
 * Time: O(n)
 */
public class LeetCode680 {
	public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 2) return true;
        int i = 0, j = s.length()-1;
        while (i < j){
            if (s.charAt(i) != s.charAt(j)){
                return (isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1));
            }
            i++;
            j--;
        }
        return true;
    }
    public boolean isPalindrome(String s, int l, int r){
        while (l < r){
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
