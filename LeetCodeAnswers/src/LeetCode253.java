/* author@ Qian Cai
 * Title@ Meeting Rooms II
 * Given an array of meeting time intervals consisting of 
 * start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.
 * Solution 1: pq, time: O(nlogn)
 * Solution 2: scan line, time: O(nlogn)
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
	 //scan line
	 class Solution2 {
		    public int minMeetingRooms(int[][] intervals) {
		        if (intervals == null || intervals.length == 0) return 0;
		        int n = intervals.length;
		        int res = 0;
		        int[][] events = new int[2*n][2];
		        int idx = 0;
		        for (int[] interval: intervals){
		            int start = interval[0], end = interval[1];
		            events[idx++] = new int[]{start, 1};
		            events[idx++] = new int[]{end, -1};
		        }
		        Arrays.sort(events, (a, b)->(a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]));
		        int opened = 0;
		        for (int[] event: events){
		            opened += event[1];
		            res = Math.max(opened, res);
		        }
		        return res;
		        
		    }
		}
}
