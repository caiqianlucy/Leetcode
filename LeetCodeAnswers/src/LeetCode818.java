/* author@ Qian Cai
 * Title@ Race Car
 * Your car starts at position 0 and speed +1 on an infinite number line.  
 * (Your car can go into negative positions.)

Your car drives automatically according to a sequence of instructions A
 (accelerate) and R (reverse).

When you get an instruction "A", your car does the following: position += speed, speed *= 2.

When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

Now for some target position, say the length of the shortest sequence of instructions to get there.
 * =================
 * time: O(tlogt)
 */
import java.util.*;
public class LeetCode818 {
	public int racecar(int target) {
        int[] dp = new int[target+1]; //dp[i] are cost to reach i
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int t = 2; t <= target; t++){
            int k = 32 - Integer.numberOfLeadingZeros(t);
            if (t == (1<<k) -1){
                dp[t] = k;
                continue;
            }
            //move k-1 step closest to target then go back j step then reverse
            for (int j = 0; j < k-1; j++){
                dp[t] = Math.min(dp[t], dp[t-(1<<(k-1)) + (1<<j)] + 2 + k - 1 + j);
            }
            //move k step pass target then go back
            dp[t] = Math.min(dp[t], dp[(1<<k) - 1 - t] + k + 1);
        }
        return dp[target];
    }
}
