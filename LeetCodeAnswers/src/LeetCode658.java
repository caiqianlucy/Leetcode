/* author@ Qian Cai
 * Title@ Find K Closest Elements
 * Given a sorted array, two integers k and x, find the k closest elements 
 * to x in the array. The result should also be sorted in ascending order. 
 * If there is a tie, the smaller elements are always preferred.
 * Binary Search
 * Time complexity: O(log(n-k))
 */
import java.util.*;
public class LeetCode658 {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length-k;
        while (left < right){
            int mid = left + (right-left)/2;
            if (x-arr[mid] > arr[mid+k]-x){
                left = mid+1;
            } else right = mid;
        }
        List<Integer> res = new ArrayList();
        for (int i = left; i < left+k; i++){
            res.add(arr[i]);
        }
        return res;
        
    }
}
