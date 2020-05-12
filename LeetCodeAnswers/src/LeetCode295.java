/* author@ Qian Cai
 * Title@ Find Median From Data Stream
 * Median is the middle value in an ordered integer list. 
 * If the size of the list is even, there is no middle value.
 *  So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two 
operations:

void addNum(int num) - Add a integer number from the data 
stream to the data structure.
double findMedian() - Return the median of all elements so 
far.
 * Time: O(logN) for addNum, O(1) for findMedian
 */
import java.util.*;
public class LeetCode295 {
	class MedianFinder {
	    PriorityQueue<Integer> lo, hi;
	    /** initialize your data structure here. */
	    public MedianFinder() {
	        lo = new PriorityQueue<Integer>((a, b)->(b-a));
	        hi = new PriorityQueue<Integer>();
	    }
	    
	    public void addNum(int num) {
	        if (lo.size() == hi.size()){
	            hi.add(num);
	            lo.add(hi.poll());
	        } else {
	            lo.add(num);
	            hi.add(lo.poll());
	        }
	    }
	    
	    public double findMedian() {
	        if (lo.size() == hi.size()) return (lo.peek() + hi.peek())/2.0;
	        return lo.peek();
	    }
	}
}
