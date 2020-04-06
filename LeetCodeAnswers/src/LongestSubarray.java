import java.util.TreeMap;

/*author@ Qian Cai
 * Given a array of integers, find the longest subarray, in which difference between any two numbers is less than or equal to D. Return the length of the subarray.
 * Time: O(nlogn), Space: 
 */
public class LongestSubarray {
   public static int longestSubarray(int[] arr, int d) {
	   if (arr == null || arr.length == 0) return 0;
	   if (arr.length == 1) return 1;	  
	   TreeMap<Integer, Integer> tm = new TreeMap();
	   int l = 0, r = 0;
	   int res = 1;
	   while (l <= r && r < arr.length) {
		   tm.put(arr[r], tm.getOrDefault(arr[r],0)+1);
		   r++;
		   int min = tm.firstKey();
		   int max = tm.lastKey();
		   while (max - min > d) {
			   tm.put(arr[l], tm.get(arr[l])-1);
			   if (tm.get(arr[l]) == 0) tm.remove(arr[l]);
			   l++;
			   min = tm.firstKey();
			   max = tm.lastKey();
		   }
		   res = Math.max(res, r-l);
	   }
	   return res;
   } 
   public static void main(String[] args) {
	   int[] arr = new int[] {1, 2, 5, 7, 6, 6};
	   int res = longestSubarray(arr, 2);
	   System.out.println(res);
   }
}