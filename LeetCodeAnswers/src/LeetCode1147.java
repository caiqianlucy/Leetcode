/* author@ Qian Cai
 * Title@ Longest Chunked Palindrome Decomposition
 * Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:

Each a_i is a non-empty string;
Their concatenation a_1 + a_2 + ... + a_k is equal to text;
For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 */
public class LeetCode1147 {
	//Greedy, time: O(n)
    public int longestDecomposition(String text) {
        int l = 0; //left starting index
        int n = text.length();
        int r = n-1; //right starting index
        int rr = n; //right boundary for right side
        int res = 0;
        while (l < r){
            if (text.charAt(l) == text.charAt(r)){
                String ls = text.substring(l, rr-r+l);
                String rs =text.substring(r, rr);
                if (ls.equals(rs)){
                    res+=2;
                    l = rr-r+l;
                    rr = r;
                }
            }
            r--;
        }
        //aba: res++   aa: res
        return l == r ? res+1: res;
    }
}
