/* author@ Qian Cai
 * Title@ Shortest Common Supersequence
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  
 * If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, 
and the characters are chosen anywhere from T) results in the string S.)
 */
public class LeetCode1092 {
	  public String shortestCommonSupersequence(String str1, String str2) {
	        //find longest common subsequence first
			int m = str1.length(), n = str2.length();
			int[][] dp = new int[m+1][n+1];
			for (int i = m-1; i >= 0; i--){
				for (int j = n-1; j >= 0; j--){
					if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i+1][j+1] + 1;
					else dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
		 	}
			StringBuilder sb = new StringBuilder();
			int i = 0, j = 0;
			while (i < m && j < n){
				//append common char
				if (str1.charAt(i) == str2.charAt(j)){
					sb.append(str1.charAt(i));
					i++;
				 	j++;
				} else {
					//str1[i] is not used as common char
					if (dp[i][j] == dp[i+1][j]){
						sb.append(str1.charAt(i++));
					} else {
						sb.append(str2.charAt(j++));
					}
				}
			}
	        while (i < m) sb.append(str1.charAt(i++));
			while (j < n) sb.append(str2.charAt(j++));

			return sb.toString();

	    }
}
