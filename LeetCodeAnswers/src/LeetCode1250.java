/*author@Qian Cai
 * 贝祖数 - 李永乐老师
https://www.youtube.com/watch?v=WGO4Tqx5owg
Title: Check If It Is a Good Array
Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.

Return True if the array is good otherwise return False.

 * Time: O(n), Space: O(1)
 */
public class LeetCode1250 {
	public boolean isGoodArray(int[] nums) {
        if (nums.length == 1) return nums[0]==1 || nums[0] == -1;
        int a = nums[0];
        for (int i = 1; i < nums.length; i++){
            a = gcd(a, nums[i]);
        }
        return a == 1;
    }
    public int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a%b);
    }
}
