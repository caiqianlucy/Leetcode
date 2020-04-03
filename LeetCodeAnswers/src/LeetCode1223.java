/*author@ Qian Cai
 * Title@ Dice Roll Simulation
 * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times. 

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
 * Time: O(n) space: O(1)
 */
public class LeetCode1223 {
	int MODULO = 1000000007;
    public int dieSimulator(int n, int[] rollMax) {
        int[][] dp = new int[6][15];
        for (int i = 0; i < 6; i++) dp[i][0] = 1;
        for (int i = 1; i < n; i++){
            int[][] temp = new int[6][15];
            for (int j = 0; j < 6; j++){
                for (int k = 0; k < 15; k++){
                    if (k == 0){
                        for (int prev = 0; prev < 6; prev++)
                            if (prev != j){
                                for (int count: dp[prev]) temp[j][k] =(temp[j][k] + count) % MODULO;
                            }
                    } else{
                        if (k < rollMax[j]) temp[j][k] = dp[j][k-1];
                    }
                }
            }
            dp = temp;
        }
        int res = 0;
        for (int i = 0; i < 6; i++){
            for (int count: dp[i]) res = (res + count) % MODULO;
        }
        return res;
    }
}
