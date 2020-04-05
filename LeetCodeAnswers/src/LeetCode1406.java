import java.util.Arrays;

/*author@ Qian Cai
 * Title@ StoneGameIII
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.

The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 * time, space: O(n)
 */
public class LeetCode1406 {
	Integer[] memo;
    int n;
    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        memo = new Integer[n+1];
        Arrays.fill(memo, null);
        memo[n] = 0;
        int res = helper(stoneValue, 0); //score alice win over bob
        if (res == 0) return "Tie";
        if (res > 0) return "Alice";
        return "Bob";
    }
    public int helper(int[] stoneValue, int idx){
        if (memo[idx] != null) return memo[idx];
        int temp = Integer.MIN_VALUE, cum = 0;
        for (int i = idx; i < Math.min(n, idx+3); i++){
           cum += stoneValue[i];
           if (cum - helper(stoneValue, i+1) > temp){
               temp = cum - helper(stoneValue, i+1);
           }
        }
        memo[idx] = temp;
        //System.out.println(memo[idx]);
        return temp;
    }
}
