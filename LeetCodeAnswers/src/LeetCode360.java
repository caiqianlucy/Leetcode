/* author@ Qian Cai
 * Title@ Sort Transformed Array
 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

 * ===============================================
 * two pointer:
 * if a > 0 from two end to middle transformed value (ax^2+bx+c) decrease
 * if a < 0 from two end to middle transformed value (ax^2+bx+c) increase
 * a == 0 is included in either case
 * Time complexity: O (n)
 */
public class LeetCode360 {
	 public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
	        int n = nums.length;
	        if (n == 1) return nums;
	        int[] res = new int[n];
	        int start = a > 0 ? n-1: 0;
	        int i = 0, j = n-1;
	        while (i <= j){
	            int iVal = nums[i]*nums[i]*a + b*nums[i] + c;
	            int jVal = nums[j]*nums[j]*a + b*nums[j] + c;
	            if (a > 0){
	                if (iVal > jVal){
	                    res[start--] = iVal;
	                    i++;
	                } else {
	                    res[start--] = jVal;
	                    j--;
	                }
	            } else {
	                if (iVal < jVal){
	                    res[start++] = iVal;
	                    i++;
	                } else {
	                    res[start++] = jVal;
	                    j--;
	                }
	            }
	        }
	        return res;
	    }
}
