/* author@ Qian Cai
 * Title@ Snapshot Array
 * Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * 
 */
import java.util.*;
public class LeetCode1146 {
	class SnapshotArray {
	    List<int[]>[] list; 
	    int id; //snapShot ID
	    int n;
	    public SnapshotArray(int length) {
	        n = length;
	        list = new List[n];
	        for (int i = 0; i < n; i++){
	            list[i] = new ArrayList();
	            list[i].add(new int[]{0, 0});
	        }
	        id = 0;
	    }
	    
	    public void set(int index, int val) {
	        List<int[]> cur = list[index];
	        if (id  == cur.get(cur.size()-1)[0]){
	            cur.get(cur.size()-1)[1] = val;
	        } else {
			    cur.add(new int[]{id, val});
	        }
	    }
	    
	    public int snap() {
	        id++;
	        return id-1;
	    }
	    
	    public int get(int index, int snap_id) {
	        List<int[]> cur = list[index];
	        return binarySearch(cur, snap_id);

	    }
		public int binarySearch(List<int[]> list, int i){
			int lo = 0, hi = list.size()-1;
			while (lo <= hi){
				int mi = lo + (hi-lo)/2;
	  			if (list.get(mi)[0] <= i) lo = mi+1;
				else hi = mi-1; 
			}
			return list.get(hi)[1];
		}

	}
}
