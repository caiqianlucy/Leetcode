/*author@ Qian Cai
 * Title@ Load Balancer
 * Your server has received a package of N incoming requests. Handling the K-th request (for K from 0 to N − 1) will take A[K] seconds.

The load balancer has to drop two acquired requests and distribute the rest of them between three workers in such a way that each worker receives a contiguous fragment of requests to handle, and finishes handling them in exactly the same moment as the other workers. No two workers should receive the same request to compute.

The load balancer's distribution of requests is performed by selecting two requests that will be dropped, and which will split the array into three contiguous parts that will be allocated to the workers. More precisely, if requests 2 and 5 are chosen by the load balancer from a package of 9 requests, then:

the 0-th to 1-st requests will be handled by the first worker,
the 3-rd to 4-th requests will be handled by the second worker,
the 6-th to 8-th requests will be handled by the third worker.
Such a distribution will be correct if each worker receives requests equalling the same total amount of handling time.

Write a function solution that, given an array A of N integers representing the time of execution of consecutive tasks, returns true if it is possible for the load balancer to choose two requests that will determine an even distribution of requests among three workers, or false otherwise.

Examples:

Given A = [1, 3, 4, 2, 2, 2, 1, 1, 2], the function should return true, as choosing requests 2 and 5 results in a distribution giving 4 seconds of handling time to each worker, as explained in the following image:

 * Amazon OA: https://leetcode.com/discuss/interview-question/506287/
 * Time: O(n^2)
 */
public class LoadBalancer {
    public static boolean solve(int[] nums) {
    	if (nums.length < 5) return false;
    	int sum = 0;
    	for (int num: nums) sum += num;
    	int n = nums.length;
    	int partOne = nums[0];
    	int partThree = nums[n-1];
    	int l = 0, r = n-1;
    	while (l < r - 2) {
    		if (partOne == partThree) {
    			if (partOne == sum - 2*partOne - nums[l+1] - nums[r-1]) {
    				return true;
    			}
    			else {
    				l++;
    				r--;
    				partOne += nums[l];
    				partThree += nums[r];
    			}
    		}
    		else if (partOne < partThree) {
    			l++;
    			partOne += nums[l];
    		} else {
    			r--;
    			partThree += nums[r];
    		}
    	}
    	return false;
    }
    public static void main(String[] args) {
    	int[] nums1 = {1, 3, 4, 2, 2, 2, 1, 1, 2};
    	int[] nums2 = {1,1,1,1};
    	int[] nums3 = {1,1,1,1,1,1};
    	System.out.println(solve(nums1));
    	System.out.println(solve(nums2));
    	System.out.println(solve(nums3));
    	
    	
    }
}
