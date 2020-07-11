/* author@ Qian Cai
 * Title@ New 21 Game
 * Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  
During each draw, she gains an integer number of points randomly from the range [1, W], 
where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she 
has N or less points?
 * Time: O(N)
 */
public class LeetCode837 {
	public double new21Game(int N, int K, int W) {
        double[] dp = new double[N+W+1];
	    for (int k = K; k <= N; k++){
		     dp[k] = 1.0;
	     }
         double sum = Math.min(N-K+1, W);
	     for (int i = K-1; i >= 0; i--){
		      dp[i] = sum/W;
              sum += dp[i] - dp[i+W];
 	     }
	       return dp[0];

    }
}
