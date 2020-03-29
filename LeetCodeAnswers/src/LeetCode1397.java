/*author@Qian Cai
 * Time, Space: O(m+n) m, n is the length of s1 and s2
 * Title@Find All Good String
 * Given the strings s1 and s2 of size n, and the string evil. Return the number of good strings.

A good string has size n, it is alphabetically greater than or equal to s1, it is alphabetically smaller than or equal to s2, and it does not contain the string evil as a substring. Since the answer can be a huge number, return this modulo 10^9 + 7.
 * 
 */
public class LeetCode1397 {
	int MOD = 1000000007;
    public int findGoodStrings(int n, String s1, String s2, String evil) {        
        return (int) dfs(0, 0, 1, 1, s1, s2, evil, kmp(evil), new Long[n][evil.length()][2][2]);
    }
    /*1. i, j we are at current position i matched evil string up to j
      2. if one of eq is 1, then it means we have to choose those >= s1 or <= s2 chars
    */
    public long dfs(int i, int j, int eq1, int eq2, String s1, String s2, String evil, int[] kmp, Long[][][][] dp){
        
        if (j == evil.length()) return 0;
        if (i == s1.length()) return 1;
        if (dp[i][j][eq1][eq2] != null) return dp[i][j][eq1][eq2];
        long res = 0L;
        for (char c = 'a'; c <= 'z'; c++){
            int nj = j;            
            while (nj > 0 && evil.charAt(nj) != c){
                nj = kmp[nj-1];
            }
            if (evil.charAt(nj) == c) nj++;
            //System.out.println(nj);
            int neq1 = c > s1.charAt(i) ? 0:1;
            int neq2 = c < s2.charAt(i) ? 0:1;
            //System.out.println(c + "Letter " + neq1 + " " + neq2);
            if (eq1 == 0 && eq2 == 0){
                res += dfs(i+1, nj, 0, 0, s1, s2, evil, kmp, dp);
                res %= MOD;
            } else if (eq1 == 1 && eq2 == 1 && c >= s1.charAt(i) && c <= s2.charAt(i)){
                res += dfs(i+1, nj, neq1, neq2, s1, s2, evil, kmp, dp);
                res %= MOD;
            } else if (eq1 == 1 && eq2 == 0 && c >= s1.charAt(i)){
                
                res += dfs(i+1, nj, neq1, 0, s1, s2, evil, kmp, dp);
                res %= MOD;
            } else if (eq2 == 1 && eq1 == 0 && c <= s2.charAt(i)){
                res += dfs(i+1, nj, 0, neq2, s1, s2, evil, kmp, dp);
                res %= MOD;
            }   
        }
        dp[i][j][eq1][eq2] = res;
        return res;
    }
    
    //KMP method to check lps(longest prefix suffix) of s
    private int[] kmp(String s){
        int[] lps = new int[s.length()];
        int i = 1, j = 0;
        while (i < s.length()){
            while (j > 0 && s.charAt(i) != s.charAt(j)){
                j = lps[j-1];
            } 
            if (s.charAt(i) == s.charAt(j)){
                j++;
            }
            lps[i++] = j;           
        }
        return lps;
    }
}
