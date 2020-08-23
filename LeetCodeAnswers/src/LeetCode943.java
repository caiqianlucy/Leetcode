/* author@ Qian Cai
 * Title@ Find the Shortest Superstring
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.
 * 
 */
import java.util.*;
public class LeetCode943 {
	//traveling sales problem
    //time: O(n^2*2^n)
    public String shortestSuperstring(String[] A) {
        int n = A.length;
        int[][] adj = new int[n][n]; //adj[i][j] is the cost from A[i] to A[j]
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                adj[i][j] = calc(A[i], A[j]);
                adj[j][i] = calc(A[j], A[i]);
            }
        }
        int[][] dp = new int[1<<n][n]; //dp[i][j] total cost to reach state i using j as last point
        int[][] path = new int[1<<n][n];
        int last = -1, min = Integer.MAX_VALUE;
        for (int i = 1; i < (1<<n); i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++){
                if ((i & (1<< j)) > 0){
                    int prev = i - (1 <<j);
                    if (prev == 0){
                        dp[i][j] = A[j].length();
                    } else{
                        for (int k = 0; k < n; k++){
                            if (dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + adj[k][j] < dp[i][j]){
                                dp[i][j] = dp[prev][k] + adj[k][j];
                                path[i][j] = k;
                            }
                        }
                    }
                }
                 if (i == (1<<n) - 1 && dp[i][j] < min){
                     min = dp[i][j];
                     last = j;
                 }
            }  
        }
        StringBuilder sb = new StringBuilder();
        int cur = (1 << n) - 1;
        Stack<Integer> stack = new Stack();
        while (cur > 0){
            stack.push(last);
            int temp = cur;
            cur -= (1 << last);
            last = path[temp][last];
        }
        int i = stack.pop();
        sb.append(A[i]);
        while (!stack.isEmpty()){
            int j = stack.pop();
            sb.append(A[j].substring(A[j].length() - adj[i][j]));
            i = j;
        }
        return sb.toString();
    }
    public int calc(String a, String b){
        for (int i = 0; i < a.length(); i++){
            if (b.startsWith(a.substring(i))){
                return b.length() - (a.length()-i);
            }
        }
        return b.length();
    }
}
