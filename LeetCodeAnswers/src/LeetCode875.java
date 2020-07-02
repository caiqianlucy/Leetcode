/* author@ Qian Cai
 * Title@ Koko Eating Bananas
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.
 * 
 */
public class LeetCode875 {
	//time: O(nlog(m)) m is the maximum number of bananas in one pile. Space: O(1)
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1, hi = piles[0];
        for (int pile: piles) hi = Math.max(pile, hi);
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            if (count(mi, piles) > H) lo = mi+1;
            else hi = mi;
        }
        return lo;
    }
    public int count(int n, int[] piles){
        int res = 0;
        for (int pile: piles){
            res += (pile-1)/n + 1;
        }
        //System.out.println("n: " + n + " res: " + res);
        return res;
    }
}
