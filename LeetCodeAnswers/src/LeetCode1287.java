/* author@ Qian Cai
 * Title@ Element Appearing More Than 25% in Sorted Array
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.
 * 
 */
public class LeetCode1287 {
	//time complexity: O(logn)
    public int findSpecialInteger(int[] arr) {
        int k = arr.length/4 + 1;
        int[] list = new int[]{k-1, k*2-1, k*3-1};
        for (int i: list){
            if (i < arr.length){
                if (getCount(arr, arr[i]) >= k) return arr[i];
            }
        }
        return -1;
    }
    public int getCount(int[] arr, int target){
        int lo = getLow(arr, target);
        int hi = getHi(arr, target);
        return hi-lo+1;
    }
    public int getLow(int[] arr, int target){
        int i = 0, j = arr.length-1;
        while (i <= j){
            int mi = i + (j-i)/2;
            if (arr[mi] == target){
                if (mi == 0 || arr[mi] > arr[mi-1]) return mi;
                else j = mi-1;
            } else if (arr[mi] < target){
                i = mi+1;
            } else j = mi-1;
        }
        return -1;
    }
     public int getHi(int[] arr, int target){
        int i = 0, j = arr.length-1;
        while (i <= j){
            int mi = i + (j-i)/2;
            if (arr[mi] == target){
                if (mi == arr.length-1 || arr[mi] < arr[mi+1]) return mi;
                else i = mi+1;
            } else if (arr[mi] < target){
                i = mi+1;
            } else j = mi-1;
        }
        return -1;
    }
}
