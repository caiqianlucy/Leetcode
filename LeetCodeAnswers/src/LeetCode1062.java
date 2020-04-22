/* author@ Qian Cai
 * Title@ Longest Repeating Substring
 * Given a string S, find out the length of the longest repeating substring(s). 
 * Return 0 if no repeating substring exists.
 * Solution:
 * 1. Perform a search by a substring length in the interval from 1 to N;
 * 2. Check if there is a duplicate substring of a given length l, time complexity O(nlogn)
 * O((n-l)l)
 * a. Linear-time slice+ hashset of already seen strings.  large space consumption
 * b. Linear-time slice+ hashset of hashes of already seen strings. moderate space consumption
 * c. Rabin-karp = constant-time slice + hashSet of hashes of already seen strings. Hashes 
 * computed with the rolling hash algorithm.moderate space consumption.
 */
import java.util.*;
public class LeetCode1062 {
	public int longestRepeatingSubstring(String S) {
        int n = S.length();
        int left = 0;
        int right = n;
        while (left <= right){
            int mid = left + (right-left)/2;
            if (search(mid, n, S)) left = mid+1;
            else right = mid-1;
        }
        return left-1;
    }
    public boolean search(int len, int n, String s){
        HashSet<Integer> seen = new HashSet();
        for (int i = 0; i < n-len+1; i++){
            String cur = s.substring(i, len+i);
            //System.out.println(cur);
            int code = cur.hashCode();
            //System.out.println(code);
            if (seen.contains(code)) return true;
            seen.add(code);
        }
        return false;       
    }
    public int longestRepeatingSubstring2(String s) {
    	int n = s.length();
    	int[] nums = new int[n];
    	for (int i = 0; i < n; i++) {
    		nums[i] = s.charAt(i) - 'a';
    	}
    	int a = 26;
    	long mod = (long)Math.pow(2, 24);
    	int left = 1, right = n;
    	while (left <= right){
            int mid = left + (right-left)/2;
            if (find(mid, a, mod, n, nums)) left = mid+1;
            else right = mid-1;
        }
        return left-1;
    }
    public boolean find(int len, int a, long mod, int n, int[] nums) {
    	long h = 0, al = 1;
    	for (int i = 0; i < len; i++) {
    		h = (h*a + nums[i])%mod;
    		al *= a % mod;
    	}
    	HashSet<Long> seen = new HashSet();
    	seen.add(h);
    	for (int start = 1; start < n - len + 1; start++) {
    		h = (h*a - nums[start-1]*al % mod + mod)%mod; // +mod in case of negative value
    		h = (h+nums[start+len-1])%mod;
    		if (seen.contains(h)) return true;
    		seen.add(h);
    	}
    	return false;   	
    }
  }

