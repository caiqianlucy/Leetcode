/* author@ Qian Cai
 * Title@ Super Egg Drop
 * You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 
 * 
 */
import java.util.*;

public class LeetCode887 {
	class Solution1 {
	    //time: O(KNlogN)
	    int[][] dp;
	    public int superEggDrop(int K, int N) {
	        dp = new int[K+1][N+1];
	        for (int i = 0; i <= K; i++) Arrays.fill(dp[i], 10001);
	        return helper(K, N);
	        
	    }
	    public int helper(int K, int N){
	        if (N == 0) return 0;
	        if (K == 1) return N;
	        if (dp[K][N] != 10001) return dp[K][N];
	        int lo = 1, hi = N;
	        while (lo + 1 < hi){
	            int mi = lo + (hi-lo)/2;
	            int mi_1 = helper(K-1, mi-1);
	            int mi_2 = helper(K, N-mi);
	            if (mi_1 < mi_2) lo = mi;
	            else if (mi_1 > mi_2) hi = mi;
	            else lo = hi = mi;
	        }
	        int res = 1 + Math.min(Math.max(helper(K-1, lo-1), helper(K, N-lo)), Math.max(helper(K-1, hi-1), helper(K, N-hi)));
	        dp[K][N] = res;
	        return res;
	    }
	}
	class Solution2 {
	    //time: O(KN)
	    public int superEggDrop(int K, int N) {
	        int[] dp = new int[N+1]; //K == 1
	        for (int i = 0; i <= N; i++){
	            dp[i] = i;
	        }
	        for (int k = 2; k <= K; k++){
	            int[] dp2 = new int[N+1];
	            int x = 1;
	            for ( int n = 1; n <= N; n++){
	                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1])){
	                    x++;
	                }
	                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
	            }
	            dp = dp2;
	        }
	        return dp[N];
	    }
	}
}
