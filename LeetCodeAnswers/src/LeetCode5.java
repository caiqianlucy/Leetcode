/* Two methods: 
 * 1. expand from center, time: o(nlogn) space: o(1)
 * 2. Manacher's algorithm, time: o(n) 
 */
public class LeetCode5  {
	public String longestPalindrome(String s) {
        if (s == null ||s.length() < 1) return "";
        int l = 0, r = 0;

        for (int i = 0; i < s.length(); i++){
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > r-l+1){
                l = i - (len-1)/2;
                r = i + len/2;
            }
        }
        return s.substring(l, r+1);
    }
    private int expand(String s, int l, int r){
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }
    public String longestPalindromeManacher(String s) {
        if (s == null || s.length() == 0) return "";
        String ns = transform(s); //transform the string to make it easier to consider both the case of expand the center the case of (i, i) and (i, i+1)
        int n = ns.length();
        int[] dp = new int[n];//dp[i] longest symetric length after i
        int center = 0, r = 0;
        for (int i = 0; i < n; i++){
            int mir = 2*center-i;
            if (i < r){
                dp[i] = Math.min(r-i, dp[mir]);
            }
            while ((i+dp[i]+1) <n && (i-dp[i]-1) >=0 && ns.charAt(i+dp[i]+1) == ns.charAt(i-dp[i]-1)){
                dp[i]++;
            }
            if (i + dp[i] > r){
                r = i+dp[i];
                center = i;
            }            
        }
        int len = 0;
        center = 0;
        for (int i = 0; i < n; i++){
            if (dp[i] > len){
                len = dp[i];
                center = i;
            }
        }
        return s.substring((center-1)/2-(len-1)/2, (center-1)/2 + len/2 + 1);       
    }
    private String transform(String s){
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()){
            sb.append('#');
            sb.append(c);
        }
        sb.append('#');
        return sb.toString();
    }
}
