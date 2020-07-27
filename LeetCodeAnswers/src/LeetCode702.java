/* author@ Qian Cai
 * Title@ Search in a sorted Array of Unknown Size
 * Given an integer array sorted in ascending order, write a function to search target
 *  in nums.  If target exists, then return its index, otherwise return -1. However, 
 *  the array size is unknown to you. You may only access the array using an ArrayReader
 *   interface, where ArrayReader.get(k) returns the element of the array at index k
 *    (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array
 out of bounds, ArrayReader.get will return 2147483647.
 * 
 */
import java.util.*;
public class LeetCode702 {
	public int search(ArrayReader reader, int target) {
        int lo = 0, hi = find(reader);
        while (lo <= hi){
            int mi = lo + (hi-lo)/2;
            if (reader.get(mi) < target){
                lo = mi + 1;
            } else if (reader.get(mi) > target){
                hi = mi-1;
            } else return mi;
        }
        return -1;
    }
    public int find(ArrayReader reader){
        int lo = 0, hi = 10000;
        while (lo <= hi){
            int mi = lo + (hi-lo)/2;
            if (reader.get(mi) == Integer.MAX_VALUE){
                hi = mi-1;
            } else {
                lo = mi+1;
            }
        }
        return hi;
    }
}
