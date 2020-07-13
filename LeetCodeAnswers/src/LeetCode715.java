/* author@ Qian Cai
 * Title@ Range Module
 * ==========================
 * treemap
 * addRange, removeRange O(n)
 * queryRange O(logn)
 */
import java.util.*;
public class LeetCode715 {
	class RangeModule {
	    TreeMap<Integer, Integer> map; //key start, val end;
	    public RangeModule() {
	        map = new TreeMap();
	    }
	    
	    public void addRange(int left, int right) {
	        if (right <= left) return;
	        Integer start = map.floorKey(left);
	        if (start == null) start = map.ceilingKey(left);
	        while (start != null && start <= right){
	            int end = map.get(start);
	            if (end >= left){
	                map.remove(start);
	                left = Math.min(left, start);
	                right = Math.max(right, end);
	            }
	            start = map.ceilingKey(end);
	        }
	        map.put(left, right);
	    }
	    
	    public boolean queryRange(int left, int right) {
	        Integer floor = map.floorKey(left);
	        return floor != null && map.get(floor) >= right;
	    }
	    
	    public void removeRange(int left, int right) {
	        if (left >= right) return;
	        Integer start = map.floorKey(left);
	        if (start == null) start = map.ceilingKey(left);
	        while (start != null && start <= right){
	            int end = map.get(start);
	            if (end >= left){
	                map.remove(start);
	                if (start < left) map.put(start, left);
	                if (end > right) map.put(right, end);
	            }
	            start = map.ceilingKey(end);
	        }
	    }
	}
}
