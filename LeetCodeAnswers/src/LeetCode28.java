/* author@ Qian Cai
 * Title@ Implement strStr()
 * Implement strStr().

Return the index of the first occurrence of needle in haystack, 
or -1 if needle is not part of haystack.
 * Time: O(m+n) Space: O(n) m = haystack.length(), n = needle.length()
 */
public class LeetCode28 {
	public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;
        int m = haystack.length();
        int n = needle.length();
        int[] lps = new int[n];
        int i = 1, j = 0;
        while (i < n){
            while (j > 0 && needle.charAt(i) != needle.charAt(j)){
                j = lps[j-1];
            }
            if (needle.charAt(i) == needle.charAt(j)) j++;
            lps[i++] = j;
        }
        i = 0;
        j = 0;
        while (i < m){
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)){
                j = lps[j-1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) j++;
            i++;
            if (j == n) return i-n;
            
        }
        return -1;
    }
}
