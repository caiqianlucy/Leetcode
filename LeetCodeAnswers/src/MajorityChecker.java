/* author@ Qian Cai
 * Title@ Online Majority Element in Subarray
 * Implementing the class MajorityChecker, which has the following API:

MajorityChecker(int[] arr) constructs an instance of MajorityChecker with 
the given array arr;
int query(int left, int right, int threshold) has arguments such that:
0 <= left <= right < arr.length representing a subarray of arr;
2 * threshold > right - left + 1, ie. the threshold is always a 
strict majority of the length of the subarray
Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right]
 that occurs at least threshold times, or -1 if no such element exists.
 * ===========================================================================
 * Solution
 * 1.use segment tree+Map, the major element have to be major in either left 
 * or right tree 
 * Time: Construct tree O(nlogn) query O(logn)
   2. Random Pick method use Map and pick random index 20 times, still have
    low chance to not pick the right idx, but is easier to write 
    Time complexity: query O(logn)
    3. Moore_majority_vote_algorithm, query O(n)
    ==========================================================================
 */
import java.util.*;
public class MajorityChecker {
	 private int[] arr;
	    private SegmentTreeNode root;
	    private Map<Integer, List<Integer>> map;
	    public MajorityChecker(int[] arr) {
	        this.arr = arr;
	        this.root = buildTree(0, arr.length-1);
	        map = new HashMap();
	        for (int i = 0; i < arr.length; i++){
	            if (!map.containsKey(arr[i])) map.put(arr[i], new ArrayList());
	            map.get(arr[i]).add(i);
	        }
	    }
	    
	    public int query1(int left, int right, int threshold) {
	        int[] cand = queryTree(left, right, root);
	        if (cand[1] >= threshold) return cand[0];
	        int l = Collections.binarySearch(map.get(cand[0]), left);
	        int r = Collections.binarySearch(map.get(cand[0]), right);
	        if (l < 0) l = -l-1;
	        if (r < 0) r = -r-2;
	        return r-l+1 >= threshold ? cand[0] : -1;
	    }
	    //random pick method
	    public int query2(int left, int right, int threshold) {
	    	for (int i = 0; i < 20; i++) {
	    		Random rand = new Random();
	    		int pivot = rand.nextInt(right-left+1) + left;
	    		int l = Collections.binarySearch(map.get(arr[pivot]), left);
	    		int r = Collections.binarySearch(map.get(arr[pivot]), right);
	    		if (l < 0) l = -l-1;
	    		if (r < 0) r = -r-2;
	    		if (r-l+1 >= threshold) return arr[pivot];
	    	}
	    	return -1;
	    }
	    //find the major element through segment tree
	    public int[] queryTree(int left, int right, SegmentTreeNode root){
	        if (root.start >= left && root.end <= right) return root.count;
	        int mid = root.start + (root.end-root.start)/2;
	        int[] res = new int[]{0, 0};
	        if (left <= mid){
	            res = merge(res, queryTree(left, right, root.left));
	        } 
	        if (right > mid){
	            res = merge(res, queryTree(left, right, root.right));
	        }
	        return res;
	    }
	    //build SegmentTree, this take NlogN time
	    public SegmentTreeNode buildTree(int start, int end){
	        if (start == end){
	            return new SegmentTreeNode(start, end, new int[]{arr[start], 1});
	        }
	        SegmentTreeNode node = new SegmentTreeNode(start, end, new int[]{0, 0});
	        
	        int mid = start + (end-start)/2;
	        node.left = buildTree(start, mid);
	        node.right = buildTree(mid+1, end);
	        node.count = merge(node.left.count, node.right.count);
	        return node;
	    }
	    public int[] merge(int[] left, int[] right){
	        if (left[0] == right[0]) return new int[]{left[0], left[1]+right[1]};
	        if (left[1] > right[1]) return new int[]{left[0], left[1]-right[1]};
	        return new int[]{right[0], right[1]-left[1]};
	    }
	    public class SegmentTreeNode{
	        int start;
	        int end;
	        int[] count; //count of the major elements
	        SegmentTreeNode left;
	        SegmentTreeNode right;
	        SegmentTreeNode(int start, int end, int[] count){
	            this.start = start;
	            this.end = end;
	            this.count = count;
	            left = null;
	            right = null;
	        }
	        
	    }
    
    public int query3(int left, int right, int threshold) {
        int major = arr[left];
        int count = 1;  //major element appears more than half of the array
        for (int i = left+1; i <= right; i++){
            if (arr[i] == major){
                count++;
            } else if (count == 0){
                major = arr[i];
                count = 1;
            } else{
                count--;
            }
        }
        count = 0;
        //System.out.println(major);
        for (int i = left; i <= right; i++){
            if (arr[i] == major) count++;
        }
        return count >= threshold ? major: -1;
    }
}
