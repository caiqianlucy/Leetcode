/* author@ Qian Cai
 * Title@ Knight Probability in Chessboard
 * time: O(k*n^2)
 * 
 */
public class LeetCode688 {
	 Double[][][] memo;
	    int N;
	    public double knightProbability(int N, int K, int r, int c) {
	        memo = new Double[N+1][N+1][K+1];
	        this.N = N;
	        return helper(K, r, c);
	    }
	    public double helper(int k, int r, int c){
	        if (r < 0 || r >= N || c < 0 || c >= N) return 0.0;
	        if (k == 0) return 1.0;
	        if (memo[r][c][k] != null) return memo[r][c][k];
	        int[][] dirs = new int[][]{{-1, -2}, {-2, -1}, {-1, 2}, {-2, 1}, {1, 2}, {1, -2}, {2, -1}, {2, 1}};
	        double res = 0.0;
	        for (int d = 0; d < 8; d++){
	            res += helper(k-1, r + dirs[d][0], c + dirs[d][1])/8;
	        }
	        memo[r][c][k] = res;
	        return res;
	    }
}
