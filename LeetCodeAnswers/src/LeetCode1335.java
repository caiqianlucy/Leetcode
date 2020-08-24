/* author@ Qian Cai
 * Title@ Minimum Difficulty of a Job Schedule
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 * 
 */
public class LeetCode1335 {
	public int minDifficulty(int[] jobDifficulty, int d) {
	       int n = jobDifficulty.length;
	        if (n < d) return -1;
	        int[][] dp = new int[d][n]; //dp[i][j] cut to i+1 days until j+1 jobs
	        dp[0][0] = jobDifficulty[0];
	        for (int i = 1; i < n; i++){
	            dp[0][i] = Math.max(dp[0][i-1], jobDifficulty[i]);
	        }
	        for (int k = 1; k < d; k++){
	            for (int i = k; i < n; i++){
	                int max = jobDifficulty[i];
	                dp[k][i] = Integer.MAX_VALUE;
	                for (int j = i; j >= k; j--){
	                    max = Math.max(max, jobDifficulty[j]);
	                    dp[k][i] = Math.min(dp[k][i], max+dp[k-1][j-1]);
	                }
	            }
	        }

	        return dp[d-1][n-1];       
	    }
}
