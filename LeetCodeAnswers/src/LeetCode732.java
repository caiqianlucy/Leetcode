/* author@ Qian Cai
 * Title@ My Calendar III
 * Implement a MyCalendarThree class to store your events. A new event can always be added.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)

For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.

Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)


My Calendar II Follow up
 */
import java.util.*;
public class LeetCode732 {
	class MyCalendarThree {
	    TreeMap<Integer, Integer> delta;
	    public MyCalendarThree() {
	        delta = new TreeMap();       
	    }
	    
	    public int book(int start, int end) {
	        delta.put(start, delta.getOrDefault(start, 0)+1);
	        delta.ptu(end, delta.getOrDefault(end, 0)-1);
	        int active = 0;
	        int ans = 0;
	        for (int d: delta.values()){
	            active += d;
	            ans = Math.max(active, ans);
	        }
	        return ans;
	    }
	}
}
