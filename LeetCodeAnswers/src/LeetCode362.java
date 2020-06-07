/* author@ Qian Cai
 * Title@ Design Hit Counter
 * Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.
 * 
 */
import java.util.*;
public class LeetCode362 {
	class HitCounter {
	    Queue<Integer> queue;
	    /** Initialize your data structure here. */
	    public HitCounter() {
	        queue = new LinkedList();
	    }
	    
	    /** Record a hit.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public void hit(int timestamp) {
	        queue.add(timestamp);
	        clean(queue, timestamp);
	    }
	    
	    /** Return the number of hits in the past 5 minutes.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public int getHits(int timestamp) {
	        clean(queue, timestamp);
	        return queue.size();
	    }
	    public void clean(Queue<Integer> queue, int timestamp){
	        while (queue.size() > 0 && timestamp-queue.peek() >= 300){
	            queue.poll();
	        }
	    }
	}
}
