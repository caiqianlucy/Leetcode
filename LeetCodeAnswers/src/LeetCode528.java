/* author@Qian Cai
 * Title@ Random Pick with Weight
 * Given an array w of positive integers, where w[i] describes the weight of index i(0-indexed), write a function pickIndex which randomly picks an index in proportion to its weight.

For example, given an input list of values w = [2, 8], when we pick up a number out of it, the chance is that 8 times out of 10 we should pick the number 1 as the answer since it's the second element of the array (w[1] = 8).
 * 
 */
public class LeetCode528 {
	int[] preSum;
    int n;
    int sum;
    public Solution(int[] w) {
        n = w.length;
        preSum = new int[n];
        preSum[0] = w[0];
        for (int i = 1; i < n; i++) preSum[i] = preSum[i-1] + w[i];
        sum = preSum[n-1];
    }
    
    public int pickIndex() {
        Random rand = new Random();
        int r = rand.nextInt(sum);
        return binarySearch(r);
        
    }
    public int binarySearch(int r){
        int lo = 0; 
        int hi = n-1;
        while (lo <= hi){
            int mi = lo + (hi-lo)/2;
            if (preSum[mi] > r && (mi == 0 || preSum[mi-1] <= r)) return mi;
            else if (preSum[mi] <= r){
                lo = mi+1;
            } else hi = mi-1;
        }
        return -1;
    }
}
