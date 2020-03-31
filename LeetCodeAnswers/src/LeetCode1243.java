import java.util.*;
/* author@ Qian Cai
 * Title@ Array Transformation
 * Given an initial array arr, every day you produce a new array using the array of the previous day.

On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:

If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
The first and last elements never change.
After some days, the array does not change. Return that final array.
 * 
 * Space: O(1) Time: O(n^m) m is the number of days that arr does not change
 */
public class LeetCode1243 {
	public List<Integer> transformArray(int[] arr) {
        List<Integer> res = new LinkedList();
        boolean change = true;
        while (change){
            change = false;
            int prev = arr[0];
            for (int i = 1; i < arr.length-1; i++){
                int c = arr[i];
                if (arr[i] < prev && arr[i] < arr[i+1]) {
                    arr[i]++;
                    change = true;
                }   
                else if (arr[i] > prev && arr[i] > arr[i+1]){
                   arr[i]--;
                   change = true;
                } 
                prev = c;
            }
        }
        for (int i = 0; i < arr.length; i++) res.add(arr[i]);
        return res;
    }
}
