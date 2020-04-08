/*author@ Qian Cai
 * Title@ Top K Frequent Words
 * Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. 
If two words have the same frequency, then the word with the lower
 alphabetical order comes first.
 * Time: O(nlogk), space: O(n)
 */
import java.util.*;
public class LeetCode692 {
	public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words){
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<String>((s1, s2) 
                                                             -> count.get(s1)==count.get(s2) ? s2.compareTo(s1) : count.get(s1)-count.get(s2));
        for (String word: count.keySet()){
            pq.offer(word);
            if (pq.size()>k) pq.poll();
        }
        List<String> ans = new ArrayList();
        while (!pq.isEmpty()) ans.add(pq.poll());
        Collections.reverse(ans);
        return ans;
    }
}
