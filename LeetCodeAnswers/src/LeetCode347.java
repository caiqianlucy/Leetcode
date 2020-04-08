/*author@Qian Cai
 * Title@ Top K Frequent Elements
 * Given a non-empty array of integers, 
 * return the k most frequent elements.
 * Time: O(nlogk), space: O(n)
 */
import java.util.*;
public class LeetCode347 {
	public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums){
            count.put(n, count.getOrDefault(n, 0)+1);
        }
        List<Integer> res = new ArrayList();
        PriorityQueue<Integer> pq = new PriorityQueue((a, b)->(count.get(a)- count.get(b)));
        for (int key: count.keySet()){
            pq.offer(key);
            if (pq.size() > k) pq.poll();
        }
        while (!pq.isEmpty()) res.add(pq.poll());
        return res;
    }
}
