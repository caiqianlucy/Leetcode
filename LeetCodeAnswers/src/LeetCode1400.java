/*author@ Qian Cai
 * Title@ Construct K Palindrome Strings
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.

Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 * Time: O(n) Space: O(1)
 */
public class LeetCode1400 {
	public boolean canConstruct(String s, int k) {
        int[] arr = new int[26]; //store the count of each character
        for (char c: s.toCharArray()){
            arr[c-'a']++;
        }
        int odd = 0, even = 0;
        for (int i = 0; i < 26; i++){
            if (arr[i] == 0) continue;
            if (arr[i] % 2 == 0) even += arr[i]/2;
            else odd += arr[i]%2;
        }
        if (odd > k || s.length() < k || odd + 2*even == k+1) return false;
        return true;
    }
}
