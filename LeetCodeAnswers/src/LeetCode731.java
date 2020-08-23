/* author@ Qian Cai
 * Title@ My Calendar II
 * time: O(n^2)
 * 
 */
import java.util.*;
public class LeetCode731 {
	//scan line
	class MyCalendarTwo {
	    TreeMap<Integer, Integer> counts;
	    public MyCalendarTwo() {
	        counts = new TreeMap();
	    }
	    
	    public boolean book(int start, int end) {
	        counts.put(start, counts.getOrDefault(start, 0) + 1);
	        counts.put(end, counts.getOrDefault(end, 0) - 1);
	        //check whether triple booked, this method can be implemented for K booked
	        int active = 0;
	        for (int val: counts.values()){
	            active += val;
	            //triple booked
	            if (active >= 3){
	                counts.put(start, counts.getOrDefault(start, 0)-1);
	                counts.put(end, counts.getOrDefault(end, 0) + 1);
	                if (counts.get(start) == 0) counts.remove(start);
	                if (counts.get(end) == 0) counts.remove(end);
	                return false;
	            }
	        }
	        return true;
	        
	    }
	}
	class MyCalendarTwo2 {
	    List<int[]> calendar;
	    List<int[]> overlaps;
	    public MyCalendarTwo2() {
	        calendar = new ArrayList();
	        overlaps = new ArrayList();
	    }
	    //[1, 3]  [2, 4] triple book
	    public boolean book(int start, int end) {
	        for (int[] itv: overlaps){
	            if (itv[0] < end && itv[1] > start) return false;
	        }
	        for (int[] itv: calendar){
	            if (itv[0] < end && itv[1] > start) overlaps.add(new int[]{Math.max(start, itv[0]), Math.min(end, itv[1])});
	        }
	        calendar.add(new int[]{start, end});
	        return true;
	    }
	}
}
