/* author@ Qian Cai
 * Title@ Find K Pairs with Smallest Sums
 * 
 * 
 */
import java.util.*;
public class LeetCode373 {
	class Solution1 {
	    //time: O(mnlogk)
	    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	        
	        List<List<Integer>> res = new LinkedList();
	        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(b[0] + b[1] -a[0]-a[1]));
	        for (int i = 0; i < nums1.length; i++){
	            for (int j = 0; j< nums2.length; j++){
	                pq.add(new int[]{nums1[i], nums2[j]});
	                if (pq.size() > k) pq.poll();
	            }
	        }
	        while (!pq.isEmpty()){
	            int[] pair = pq.poll();
	            List<Integer> list = new LinkedList();
	            list.add(pair[0]);
	            list.add(pair[1]);
	            res.add(list);
	        }
	       Collections.reverse(res);
	        return res;
	    }
	}
	class Solution2 {
	    //time: O(klogk)
	    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	        
	        List<List<Integer>> res = new LinkedList();
	        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return res;
	        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(nums1[a[0]] + nums2[a[1]] -nums1[b[0]]-nums2[b[1]]));
	        for (int i = 0; i < Math.min(nums1.length, k); i++){
	                pq.add(new int[]{i, 0});
	        }
	        while (res.size() < k && !pq.isEmpty()){
	            int[] pair = pq.poll();
	            List<Integer> list = new LinkedList();
	            list.add(nums1[pair[0]]);
	            list.add(nums2[pair[1]]);
	            res.add(list);
	            if (pair[1] + 1 < nums2.length) pq.add(new int[]{pair[0], pair[1] + 1});
	        }
	        return res;
	    }
	}
}
