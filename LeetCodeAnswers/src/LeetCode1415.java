/* author@ Qian Cai
 * Title@ kth String
 * A happy string is a string that:

consists only of letters of the set ['a', 'b', 'c'].
s[i] != s[i + 1] for all values of i from 1 to s.length - 1 
(string is 1-indexed).
For example, strings "abc", "ac", "b" and "abcbabcbcb" are 
all happy strings and strings "aa", "baa" and "ababbc" are 
not happy strings.

Given two integers n and k, consider a list of all happy strings
 of length n sorted in lexicographical order.

Return the kth string of this list or return an empty string if
 there are less than k happy strings of length n.
 * 
 */
import java.util.*;
public class LeetCode1415 {
	public String getHappyString(int n, int k) {
        int total_comb = 3* ((int)(Math.pow(2, n-1)));
        if (k > total_comb) return "";
        StringBuilder res = new StringBuilder();
        char[] dict = new char[]{'a', 'b', 'c'};
        Map<Character, char[]> map = new HashMap();
        map.put('a', new char[]{'b', 'c'});
        map.put('b', new char[]{'a', 'c'});
        map.put('c', new char[]{'a', 'b'});
        --k;
        int comb = total_comb/3;
        int idx = k/comb;
        char last = dict[idx];
        k-=idx*comb;
        res.append(last);
        comb /= 2;
        while (res.length() < n){
            idx = k/comb;
            char c = map.get(last)[idx];
            res.append(c);
            last = c;
            k -= idx*comb;
            comb /= 2;
        }
        return res.toString();
    }
}
