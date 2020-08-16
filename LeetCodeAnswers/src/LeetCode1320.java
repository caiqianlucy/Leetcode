/* author@ Qian Cai
 * Title@ Minimum Distance to Type a Word Using Two Fingers
 * time: O(n) space: O(1)
 * Solution 1: dp
 * 
 * Solution 2: further optimized based on hand symmetry
 * 
 */
import java.util.*;
public class LeetCode1320 {
	class Solution1 {
	    public int minimumDistance(String word) {
	        int[][] prev = new int[27][27]; //prev[i][j]: accumutive min total Distance,  position of left(i) and right hand(j),  0-25 represent a-z, 26 represent freehand
	        for (int i = 0; i < 27; i++) Arrays.fill(prev[i], Integer.MAX_VALUE);
	        prev[26][26] = 0; //starting with freehand
	        for (char c: word.toCharArray()){
	            int i = c-'A';
	            int[][] cur = new int[27][27];
	            for (int j = 0; j < 27; j++) Arrays.fill(cur[j], Integer.MAX_VALUE);
	            //left hand move to i position
	            for (int i1 = 0; i1 < 27; i1++){
	                for (int i2 = 0; i2 < 27; i2++){
	                    if (prev[i1][i2] != Integer.MAX_VALUE){
	                        cur[i][i2] = Math.min(prev[i1][i2] + move(i1, i), cur[i][i2]);
	                    }                    
	                }      
	            }
	            //right hand move to i position
	            for (int i2 = 0; i2 < 27; i2++){
	                for (int i1 = 0; i1 < 27; i1++){
	                    if (prev[i1][i2] != Integer.MAX_VALUE){
	                         cur[i1][i] = Math.min(prev[i1][i2] + move(i2, i), cur[i1][i]);
	                    }        
	                }
	            }
	            prev = cur;
	        }
	        return min(prev);
	    }
	    public int move(int from, int to){
	        if (from == 26) return 0;
	        return Math.abs((from-'A')/6 -(to-'A')/6) + Math.abs((from-'A')%6-(to-'A')%6);
	    }
	    public int min(int[][] prev){
	        int res = Integer.MAX_VALUE;
	        for (int i = 0; i < prev.length; i++){
	            for (int j = 0; j < prev[0].length; j++){
	                res = Math.min(res, prev[i][j]);
	            }
	        }
	        return res;
	    }
	}
	class Solution2 {
	    public int minimumDistance3D(String word) {
	         int n = word.length();
	         int[][][] dp = new int[n+1][27][27];//dp[pos][i][j] means current index of the word, the pos of left hand, right hand ; i == 26 or j == 26 means left hand or right hand is free hand
	        
	        
	         for (int pos = 1; pos <= n; pos++){
	             int c = word.charAt(pos-1)-'A';
	             for (int i = 0; i < 27; i++){
	                 for (int j = 0; j < 27; j++){
	                     dp[pos][i][j] = Math.min(dp[pos-1][i][c]+getDistance(j, c), dp[pos-1][c][j]+getDistance(i, c));
	                 }
	             }
	         }
	        return dp[n][26][26];
	             
	    }
	    public int minimumDistance(String word) {
	         int n = word.length();
	         if (n <= 2) return 0;
	         int[] dp = new int[27];//when we iterate to idx i, one hand has to be on idx i-1, the other hand could be position A-Z, dp[k] stores the minDistance so far when the other hand is at (char)('A'+k)
	                 
	         for (int pos = 1; pos < n; pos++){
	             int c = word.charAt(pos)-'A';
	             int last = word.charAt(pos-1)-'A'; //one hand must be at the previous position
	             int[] temp = new int [27]; //cost the other hand at new position
	             int cost = Integer.MAX_VALUE; //cost last hand stay there, the other hands move to current position
	             for (int i = 0; i < 27; i++){
	                  temp[i] = dp[i] + getDistance(last, c);
	                 cost = Math.min(cost, dp[i] + getDistance(i, c));
	             }
	             temp[last] = Math.min(cost, temp[last]);
	             dp = temp.clone();
	         }
	        int res = Integer.MAX_VALUE;
	        for (int dis: dp) res = Math.min(dis, res);
	        return res;
	             
	    }
	    public int getDistance(int i, int j){
	        if (i == 26) return 0;
	        return Math.abs(getIdx(i)[0] -getIdx(j)[0]) + Math.abs(getIdx(i)[1] - Math.abs(getIdx(j)[1]));
	    }
	    public int[] getIdx(int i){
	        int[] idx = new int[2];
	        idx[0] = i/6;
	        idx[1] = i%6;
	        return idx;
	    }
	}
}
