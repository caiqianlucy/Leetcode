/* author@ Qian Cai
 * Title@ Cracking the Safe
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 */
import java.util.*;
public class LeetCode753 {
	/*Euler circuit, Hierholzer's Algorithm
	K^n-1 nodes, each node has k edges
	a graph has equal in-degree and out-degree has euler circuit, ,from a vertex u we walk through edges until we get stuck (can only be u)
	n*k^n
	*/
	class Solution {
	    Set<String> seen; // keep track of edges
	    StringBuilder ans;
	    public String crackSafe(int n, int k) {
	        if (n == 1 && k == 1) return "0";
	        seen = new HashSet();
	        ans = new StringBuilder();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < n-1; i++){
	            ans.append("0");        
	        }
	        
	        for (int i = 0; i < (int)(Math.pow(k, n)); i++){
	            int size = ans.length();
	            String prev = ans.toString().substring(size-n+1);
	            for (int j = k-1; j >= 0; j--){
	                String next = prev + j;
	                if (!seen.contains(next)){
	                    seen.add(next);
	                    ans.append(j);
	                    break;
	                }
	            }
	        }
	        return ans.toString();
	        
	    }
	}
	//Traditional Euler path
	class Solution2 {
	    Set<String> seen; // keep track of edges
	    StringBuilder ans;
	    public String crackSafe(int n, int k) {
	        if (n == 1 && k == 1) return "0";
	        seen = new HashSet();
	        ans = new StringBuilder();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < n-1; i++){
	            sb.append("0");        
	        }
	        
	        dfs(sb.toString(), k);
	        ans.append(sb.toString());
	        return ans.toString();
	        
	    }
	    public void dfs(String node, int k){
	        for (int i = 0; i < k; i++){
	            String nei = node + i;
	            if (!seen.contains(nei)){
	                seen.add(nei);
	                dfs(nei.substring(1), k);
	                ans.append(i);
	            }
	        }
	    }
	}
	
}
