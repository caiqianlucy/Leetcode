/* author@ Qian Cai
 * Title@ My Calender I
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * =======================
 * time: O(nlogn)
 * space: O(n)
 */
import java.util.*;
public class LeetCode729 {
	class MyCalendar {
	    TreeMap<Integer, Integer> tm;
	    public MyCalendar() {
	        tm = new TreeMap();
	    }
	    
	    public boolean book(int start, int end) {
	        Integer lower = tm.floorKey(start);
	        Integer hi = tm.ceilingKey(start);
	        if (lower != null && tm.get(lower) > start) return false;
	        if (hi != null && hi < end) return false;
	        if (lower != null && tm.get(lower) == start){
	            start = lower;
	            tm.remove(lower);
	        }
	        if (hi != null && hi == end){
	            end = tm.get(hi);
	            tm.remove(hi);
	        }
	        tm.put(start, end);
	        return true;
	    }
	}
}
