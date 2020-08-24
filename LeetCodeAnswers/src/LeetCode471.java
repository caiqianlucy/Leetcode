/* author@ Qian Cai
 * Title@ Encode String with Shortest Length
 * 
 * Similar problem: 459
 */
public class LeetCode471 {
	public String encode(String s) {
	       if (s.length() <= 4)
	           return s;
	       int n = s.length();
	       String[][] dp = new String[n][n];
	       for (int l = 1; l <= n; l++) {
	           for (int i = 0; i <= n-l; i++) {
	               int j = i+l-1;
	               if (l <= 4) {
	                   dp[i][j] = s.substring(i,j+1);
	               } else {
	                   dp[i][j] = getShortestEncode(s.substring(i, j+1), i, dp);
	                   for (int k = i; k < j; k++) {
	                       if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length())
	                           dp[i][j] = dp[i][k]+dp[k+1][j];
	                   }
	               }
	           }
	       }
	       return dp[0][n-1];
	   }
	   
	   private String getShortestEncode(String s, int start, String[][] dp) {
	       int n = s.length();
	       String ret = s;
	       int l = getKMPTailIndex(s);
	       int m = n-l;
	       String rep = s.substring(0, m);
	       if (m != n && n % m == 0) {
	           /*for (int i = 0; i < n; i += m) {
	               if (!s.substring(i, i+m).equals(rep))
	                   return ret;
	           }*/
	           String tmp = n/m + "[" + dp[start][start+m-1] + "]";
	           if (tmp.length() < ret.length())
	               return tmp;
	       }
	       return ret;
	   }
	   
	   private int getKMPTailIndex(String s) {
	       int n = s.length();
	       char[] cs = s.toCharArray();
	       int[] kmp = new int[n];
	       int i = 1;
	       int j = 0;
	       while (i < n) {
	           while (j > 0 && s.charAt(i) != s.charAt(j)){
	               j = kmp[j-1];
	           }
	           if (s.charAt(i) == s.charAt(j)) j++;
	           kmp[i++] = j;
	       }
	       return kmp[n-1];
	   }
	   //using (s+s).indexOf(s, 1) to find out pattern
	   class Solution {
		   public String encode(String s) {
		       if (s.length() <= 4)
		           return s;
		       int n = s.length();
		       String[][] dp = new String[n][n];
		       for (int l = 1; l <= n; l++) {
		           for (int i = 0; i <= n-l; i++) {
		               int j = i+l-1;
		               if (l <= 4) {
		                   dp[i][j] = s.substring(i,j+1);
		               } else {
		                   dp[i][j] = getShortestEncode(s.substring(i, j+1), i, dp);
		                   for (int k = i; k < j; k++) {
		                       if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length())
		                           dp[i][j] = dp[i][k]+dp[k+1][j];
		                   }
		               }
		           }
		       }
		       return dp[0][n-1];
		   }
		   
		   private String getShortestEncode(String s, int start, String[][] dp) {
		       int n = s.length();
		       String ret = s;
		       int m = (s+s).indexOf(s, 1);
		       if (m > 0 && m < s.length()) {
		           /*for (int i = 0; i < n; i += m) {
		               if (!s.substring(i, i+m).equals(rep))
		                   return ret;
		           }*/
		           String tmp = n/m + "[" + dp[start][start+m-1] + "]";
		           if (tmp.length() < ret.length())
		               return tmp;
		       }
		       return ret;
		   }
		 
		}
}
