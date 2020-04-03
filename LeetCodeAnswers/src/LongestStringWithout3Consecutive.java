/*author@ Qian Cai
 * Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are same. There can be at max A 'a', B 'b' and C 'c'.
 * Time：O(nlogm) Space: O(m) n is total length of string, m is the number of distinct letters
 */
import java.util.*;
public class LongestStringWithout3Consecutive {
    public static String genenrateString(Map<Character, Integer> map) {
    	PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>((a, b)->b.getValue()-a.getValue());
    	int cnt = 0;
    	for (Map.Entry<Character, Integer> e: map.entrySet()) {
    		cnt+=e.getValue();
    		pq.add(e);
    	}
    	Map.Entry<Character, Integer> onHold = null;
    	StringBuilder sb = new StringBuilder();
    	while (!pq.isEmpty()) {
    		Map.Entry<Character, Integer> cur = pq.poll();
    		sb.append(cur.getKey());
    		if (onHold != null) {
    			pq.add(onHold);
    			onHold = null;
    		} 
    		int count = cur.getValue();
    		if (count > 1) {
    			cur.setValue(count-1);
    			//if two consecutive character is the same, put it on hold without adding back to pq
    			if (sb.length() >= 2 && cur.getKey() == sb.charAt(sb.length()-2)) {
    				onHold = cur;
    			} else {
    				pq.add(cur);
    			}
    		}
    	}
    	return sb.length() == cnt ? sb.toString(): "";
    }
}
