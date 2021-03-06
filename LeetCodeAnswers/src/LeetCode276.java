/* author@ Qian Cai
 * title@ Paint Fence
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
 * 
 */
public class LeetCode276 {
	public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k*k;
        int same = k, diff = k*(k-1);
        for (int i = 3; i <= n; i++){
            int temp = same;
            same = diff;  // n = 3, same = 6
            diff = temp*(k-1) + diff*(k-1);
        }
        return same+diff;
    }
}
