/* author@ Qian Cai
 * Title@ Meeting Rooms II
 * Given an array of meeting time intervals consisting of 
 * start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.
 * time: O(nlogn)
 */
import java.util.*;
public class LeetCode253 {
	 public int minMeetingRooms(int[][] intervals) {
	        int res = 0;
	        PriorityQueue<Integer> pq =  new PriorityQueue();
	        Arrays.sort(intervals, (a, b)->(a[0]-b[0]));
	        for (int[] interval: intervals){
	            int start = interval[0], end = interval[1];
	            
	            if (pq.isEmpty()) {
	                res++;
	                pq.add(end);
	            }
	            else {
	                int last = pq.poll();
	                if (last > start){
	                    res++;
	                    pq.add(last);
	                }
	                pq.add(end);
	            }
	        }
	        return res;
	    }
}
