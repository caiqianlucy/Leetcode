/* author@ Qian Cai
 * Title@ Hand of straight Same as LeetCode1296
 * Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
 * ========================================================
 * Solution 1: PriorityQueue: time: O(n^2) space: o(n)
 * Solution 2: time: O(mlogm + wm) space: O(m)
 * Solution 3: HashMap+Queue   time: O(mlogm) space: O(Math.min(m, k)) m is the distince number in the array
 */
import java.util.*;
public class LeetCode846 {
	public boolean isNStraightHand1(int[] hand, int W) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int c: hand) pq.add(c);
        while (!pq.isEmpty()){
		int start = pq.poll(); //time O(logn)
		for (int i = start+1; i < start + W; i++){
			if (!pq.remove(i)) return false; //O(n) for finding i and remove it
		}
        }
        return true;

    }
	public boolean isNStraightHand2(int[] hand, int W){
		TreeMap<Integer, Integer> tm = new TreeMap();//key: distinct integer, val: count of key
            for (int h: hand){
			    tm.put(h, tm.getOrDefault(h, 0)+1);
		    }
		for (int k: tm.keySet()){
			if (tm.get(k) == 0) continue;
			for (int i = 1; i < W; i++){
				if(!tm.containsKey(i+k) || tm.get(i+k) < tm.get(k)) return false;
				tm.put(i+k, tm.get(i+k)-tm.get(k));
  			}
		}
          return true;
	}
	public boolean isNStraightHand3(int[] hand, int W){
		TreeMap<Integer, Integer> tm = new TreeMap();//key: distinct integer, val: count of key
		Queue<Integer> queue = new LinkedList(); //keeps count of all growing lists
		Integer prev = null;
        int opened = 0;
            for (int h: hand){
			tm.put(h, tm.getOrDefault(h, 0)+1);
		}
		for (int k: tm.keySet()){
			if ((prev != null && (k != prev+1) && opened != 0) || tm.get(k) < opened) return false;
          
			queue.add(tm.get(k) - opened); //for rest of k which can not appending to existing lists, start a new list
            opened = tm.get(k);
			if (queue.size() == W){
				opened = tm.get(k)-queue.poll(); //the first lists forms W numbers, are now complete lists
			}
			prev = k;
		}
          return opened == 0;

	}
}
