import java.util.*;
public class RestoreTheArray {
	static int MOD = 1000000007;
    static int[] dp;
    public static int numberOfArrays(String s, int k) {
        dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return helper(s, 0, k);
    }
    public static int helper(String s, int i, int k){        
        if (i == s.length()) return 1;
        if (s.charAt(i) == '0'){
            dp[i] = 0;
            return 0;
        }
        if (dp[i] != -1) return dp[i];
        int n = s.length();
        long res = 0;
        for (int j = i+1; j <= Math.min(n, i+10); j++){
            if (Long.valueOf(s.substring(i, j)) > (long)k) break; 
            res += helper(s, j, k);
            res %= MOD;
        }
        
        dp[i] = (int)res;
        return (int)res;
    }
    public static void main(String[] args) {
    	String s = "1111111111111";
    	int k = 2000;
    	System.out.println(numberOfArrays(s, k));
    }
}
