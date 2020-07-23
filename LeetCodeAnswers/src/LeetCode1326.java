/* author@ Qian Cai
 * Title@ Minimum Number of Taps to Open to Water a Garden
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 * =============================================
 * Solution 1:
 * Dynamic Programming: time: O(n^2), space: O(n)
 * Solution 2:
 * Interval sorting + merging
 * time: O(nlogn), space: O(n)
 * Solution 3:
 * interval + stack
 * time: O(n), space: O(n)
 * Solution 4:
 * Similar to Jump Game II, 
 * Get max water range from each start
 * time: O(n), space: O(n)
 */
import java.util.*;
public class LeetCode1326 {
	class Solution1 {
	    //Every valid answer should be less than n+2, because there are only n+1 taps, so initialize the dp[] with n+2 to represent no valid taps at first place.
	    public int minTaps(int n, int[] ranges) {
	        int res = Integer.MAX_VALUE;
	        int[] dp = new int[n+1];
	        Arrays.fill(dp, n+2);
	        dp[0] = 0;
	        for (int i = 0; i <= n; i++){
	            for (int j = Math.max(0, i-ranges[i]+1); j <= Math.min(i+ranges[i], n); j++)
	                dp[j] = Math.min(dp[j], dp[Math.max(0, i-ranges[i])] + 1);
	        }
	        return dp[n] < n+2 ? dp[n]:-1;
	        
	    }
	}
	class Solution2 {
	    public int minTaps(int n, int[] ranges) {
	        int[][] intervals = new int[n+1][2];
	        for (int i = 0; i <= n; i++){
	            intervals[i][0] = i - ranges[i];
	            intervals[i][1] = i + ranges[i];
	        }
	        Arrays.sort(intervals, (a, b)->(a[0]==b[0] ? b[1]-a[1]: a[0]-b[0]));
	        int ans = 0;
	        int right = 0;
	        int idx = 0;
	        while (idx <= n){
	            int newRight = right;
	            //max new right boundary with adding one tap
	            while (idx <= n && intervals[idx][0] <= right){
	                newRight = Math.max(newRight, intervals[idx++][1]);
	            }
	            if (newRight == right) return -1;
	            ans++;
	            right = newRight;
	            if (right >= n) return ans;
	            
	        }
	        return -1;
	    }
	}
	class Solution3 {
	    public int minTaps(int n, int[] ranges) {
	        int[][] intervals = new int[n+1][2];
	        for (int i = 0; i <= n; i++){
	            intervals[i][0] = i - ranges[i];
	            intervals[i][1] = i + ranges[i];
	        }
	        Stack<Integer> stack = new Stack(); //store end of all used tap
	        
	        for (int i = 0; i <= n; i++){
	            int l = intervals[i][0], r = intervals[i][1];
	            if (l <= 0) {
	                stack = new Stack();
	                stack.push(r);               
	            }
	            else {
	                if (!stack.isEmpty() && (l > stack.peek() || r <= stack.peek())) continue;
	                int last = -1;
	                while (!stack.isEmpty() && l <= stack.peek()){
	                last = stack.pop();
	                }
	               if (last != -1) stack.push(last);
	               stack.push(r);
	               
	            }
	            if (r >= n) return stack.size();
	        }
	        return -1;
	    }
	}
	class Solution4 {
	    public int minTaps(int n, int[] ranges) {
	        int[] maxJump = new int[n+1];
	        for (int i = 0; i <= n; i++){
	            int left = Math.max(0, i-ranges[i]), right = i + ranges[i];
	            maxJump[left] = Math.max(maxJump[left], right);
	        }
	        int left = 0, right = 0, maxRight = 0;
	        int res = 1;
	        while (left <= n){
	            for (int i = left; i <= right; i++){
	                maxRight = Math.max(maxRight, maxJump[i]);
	            }
	            if (maxRight >= n) return res;
	            if (maxRight == right) return -1; // cannot extend anymore
	            res++;
	            left = right+1;
	            right = maxRight;
	        }
	        
	        return -1;
	    }
	}
	
}
