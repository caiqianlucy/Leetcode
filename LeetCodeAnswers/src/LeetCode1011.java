/* author@ Qian Cai
 * Title@ Capacity to ship packages within D days
 * A conveyor belt has packages that must be shipped from one port to another within D days.

The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 * 
 */
public class LeetCode1011 {
	public int shipWithinDays(int[] weights, int D) {
        int lo = 0; 
        int hi = 0;
        for (int weight: weights){
            lo = Math.max(lo, weight);
            hi += weight;
        }
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            if (!isPossible(weights, D, mi)) lo = mi+1;
            else hi = mi;
        }
        return lo;
    }
    public boolean isPossible(int[] weights, int D, int w){
        int sum = 0;
        for (int weight: weights){
            if (sum + weight <= w){
                sum += weight;
            } else {
                D--;
                sum = weight;
            }
        }
        return D > 0;
    }
}
