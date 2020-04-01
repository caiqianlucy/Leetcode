/*author@Qian Cai
 * Title@ Create Maximum Number
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.
 * Time: O(n^2) Space: O(n)
 */
public class LeetCode321 {
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (k == 0) return new int[0];
        int n = nums1.length;
        int m = nums2.length;
        int[] arr1 = new int[k];
        int[] arr2 = new int[k];
        int[] res = new int[k];
        for (int i = Math.max(0, k-m); i <= Math.min(k, n); i++){
            maxArray(nums1, arr1, i);
            maxArray(nums2, arr2, k-i);
            res = merge(arr1, i, arr2, k-i, res);
        }
        return res;
    }
    public void maxArray(int[] nums, int[] arr, int len){
        if (len == 0) return;
        for (int i = 0, j = 0; i < nums.length; i++){
            while (j > 0 && nums.length - i + j > len && nums[i] > arr[j-1]) j--;
            if (j < len) arr[j++] = nums[i];
        }
    }
    public int[] merge(int[] arr1, int len1, int[] arr2, int len2, int[] res){
        int[] merge = new int[len1+len2];
        int i = 0, i1 = 0, i2 = 0;
        while (i1 < len1 || i2 < len2){
            if (greater(arr1, i1, len1, arr2, i2, len2)) merge[i++] = arr1[i1++];
            else merge[i++] = arr2[i2++];
        }
        if (greater(merge, 0, res.length, res, 0, res.length)) return merge;
        
        return res;
        
    }
    public boolean greater(int[] arr1, int i1, int len1, int[] arr2, int i2, int len2){
          while (i1 < len1 && i2 < len2 && arr1[i1] == arr2[i2]){
              i1++;
              i2++;
          }
          return i2 == len2 || (i1 < len1 && arr1[i1] > arr2[i2]);
    }
}
