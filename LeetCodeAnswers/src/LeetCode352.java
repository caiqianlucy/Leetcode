/* author@ Qian Cai
 * Title@ Data Stream as Disjoint Intervals
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:


 * 
 */
import java.util.*;

public class LeetCode352 {
	class SummaryRanges {
	    TreeMap<Integer, Integer> tm;
	    /** Initialize your data structure here. */
	    public SummaryRanges() {
	        tm = new TreeMap();
	    }
	    
	    public void addNum(int val) {
	        Integer lo = tm.floorKey(val);
	        Integer hi = tm.ceilingKey(val);
	        if ((lo != null && tm.get(lo) >= val) || (hi != null && hi == val)) return;
	        int start = val, end = val;
	        if (lo != null && tm.get(lo) == val-1){
	            start = lo;
	            tm.remove(lo);
	        }
	        if (hi != null && hi == val+1){
	            end = tm.get(hi);
	            tm.remove(hi);
	        }
	        tm.put(start, end);
	    }
	    
	    public int[][] getIntervals() {
	        int[][] res = new int[tm.size()][2];
	        int idx = 0;
	        for (int key: tm.keySet()){
	            res[idx][0] = key;
	            res[idx++][1] = tm.get(key);
	        }
	        return res;
	    }
	}
}
