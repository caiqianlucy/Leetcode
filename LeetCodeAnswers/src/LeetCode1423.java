/* author@ Qian Cai
 * Title@ Maximum Points You Can Obtain from Cards
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * ============================================================================================
 * Solution 1:
 * Step 1, pre-processing to get preSum
 * step 2, find the minimum value of window n-k return whole sum - minimum sum of window n-k
 * time: O(n), space: O(n)
 * 
 * Solution 2:
 * Step 1. Calculate sum of the first k value arr[0, k-1]
 * step 2. k step Sliding window from right to left, 
 * to find the max value by of window [0, k-i) + [n-i, n) by taking arr[n-i] instead of arr[k-i] 
 * time: O(k), space: O(1)
 * 
 */
public class LeetCode1423 {
	public int maxScore1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        
        int[] prefixSum = new int[n];
        prefixSum[0] = cardPoints[0];
        for (int i = 1; i < n; i++) prefixSum[i] = prefixSum[i-1] + cardPoints[i];
        int min = Integer.MAX_VALUE;
        k = n-k;
        if (k == 0) return prefixSum[n-1];
        for (int i = k-1; i < n; i++){
            min = Math.min(min, prefixSum[i] - (i == k-1 ? 0:prefixSum[i-k]));
        }
        return prefixSum[n-1]-min;
    }
	public int maxScore2(int[] cardPoints, int k) {
        int temp = 0, res = 0;
        int n = cardPoints.length;
        for (int i = 0; i < k; i++) temp += cardPoints[i];
        res = temp;
        for (int i = 1; i <= k; i++){
            temp += (cardPoints[n-i]-cardPoints[k-i]);
            res = Math.max(res, temp);
        }
        return res;
    }
}
