/* author@ Qian Cai
 * Title@ Sliding Window Median
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 */
import java.util.*;
public class LeetCode480 {
	public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //maxHeap
        PriorityQueue<Integer> lo = new PriorityQueue<Integer>((a, b)->(a == b ? 0: a > b ? -1:1));
        //minHeap
        PriorityQueue<Integer> hi = new PriorityQueue();
        for (int i = 0; i < k; i++){
            lo.add(nums[i]);
        }
        for (int i = 0; i <k/2; i++){
            hi.add(lo.poll());
        }
        double[] res = new double[n-k+1];
        int idx = 0;
        res[idx++] = (k%2 ==1) ? lo.peek()/1.0 : (lo.peek()/2.0 + hi.peek()/2.0);
        int bal = lo.size()-hi.size();
        Map<Integer, Integer> count = new HashMap(); //keep all the elements that should be removed but not on the top of two heaps
        for (int i = k; i < n; i++){
            int left = nums[i-k];
            if (left <= lo.peek()){
                bal--; //remove from lower side
            } else bal++; //remove from higher side
            count.put(left, count.getOrDefault(left, 0) + 1);
            
            if (bal > 0){
                lo.add(nums[i]);
                hi.add(lo.poll());
                bal--;
            } else {
                hi.add(nums[i]);
                lo.add(hi.poll());
                bal++;               
            }
            while (!lo.isEmpty() && count.containsKey(lo.peek())){
               int cur = lo.poll();
                count.put(cur, count.get(cur)-1);
                if (count.get(cur) == 0) count.remove(cur);
            }
            while (!hi.isEmpty() && count.containsKey(hi.peek())){
                int cur = hi.poll();
                count.put(cur, count.get(cur)-1);
                if (count.get(cur) == 0) count.remove(cur);
            }
            //System.out.println(idx);
            //System.out.println(lo.peek());
            res[idx++] = (k%2 ==1) ? lo.peek()/1.0 : (lo.peek()/2.0 + hi.peek()/2.0);
        }
        return res;
        
        
        
    }
}
