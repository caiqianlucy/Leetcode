import java.util.Arrays;

/*author@ Qian Cai
 * Title@ Reducing Dishes
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]

Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

 
 * Time: O(nlogn) Space: O(1)
 */
public class LeetCode1402 {
	public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int res = 0, n = satisfaction.length, sum = 0;
        for (int i = n-1; i >= 0; i--){
            if (satisfaction[i] + sum > 0) res += satisfaction[i] + sum;
            else return res;
            sum += satisfaction[i];
        }
        return res;
    }
}
