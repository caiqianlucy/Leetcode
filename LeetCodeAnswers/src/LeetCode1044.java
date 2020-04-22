import java.util.HashSet;

/* author@ Qian Cai
 * Title@ longest duplicate substring
 * Given a string S, consider all duplicated substrings: 
 * (contiguous) substrings of S that occur 2 or more times.  
 * (The occurrences may overlap.) Return any duplicated substring that has the 
 * longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 * Solution
 * Follow-up LeetCode1062
 */
public class LeetCode1044 {
	public String longestDupSubstring(String s){
    	int n = s.length();
    	int[] nums = new int[n];
    	for (int i = 0; i < n; i++) {
    		nums[i] = s.charAt(i) - 'a';
    	}
    	int a = 26;
    	long mod = (long)Math.pow(2, 32);
    	int left = 1, right = n;
    	while (left <= right){
            int mid = left + (right-left)/2;
            if (find(mid, a, mod, n, nums) > -1) left = mid+1;
            else right = mid-1;
        }
    	int idx = find(left-1, a, mod, n, nums);
        return s.substring(idx, idx+left-1);
    }
    public int find(int len, int a, long mod, int n, int[] nums) {
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
    		if (seen.contains(h)) return start;
    		seen.add(h);
    	}
    	return -1;   	
    }
}
