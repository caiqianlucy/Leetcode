/* author@ Qian Cai
 * Title@ Kth Ancestor of a Tree Node
 * You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.


path compression, save only the 2^i power ancestors
 
 * 
 */
import java.util.*;
public class LeetCode5456 {
	class TreeAncestor {
	    int[][] pars;
	    int[] pow = new int[17];
	    int n;
	    public TreeAncestor(int n, int[] parent) {
	        init();
	        pars = new int[n][17];
	        this.n = n;
	        boolean[] isNonLeaf = new boolean[n];
	        isNonLeaf[0] = true;
	        for (int i = 1; i < n; i++){
	            isNonLeaf[parent[i]] = true;
	        }
	        for (int i = 0; i < n; i++) Arrays.fill(pars[i], -1);
	        for (int i = 0; i < n; i++){
	            //traverse from leaf to store 2^i parent for each node
	            if (!isNonLeaf[i]){
	                //System.out.println(i);
	                build(i, pars, parent);
	            }
	        }
	    }
	    public void build(int cur, int[][] pars, int[] parent){
	        if (cur == 0) return;
	        int[] path = new int[n];
	        int i = cur;
	        for (int j = 0; i != -1; j++){
	            path[j] = i;
	            for (int k = 0; j-pow[k] >= 0; k++){
	                pars[path[j-pow[k]]][k] = i;
	            }
	            i = parent[i];
	        }

	    }
	    public int getKthAncestor(int node, int k) {
	        if (k == 0) return node;
	        for (int i = 16; i >= 0 && node != -1; i--){
	            if (k >= pow[i]){
	                node = pars[node][i];
	                k -= pow[i];
	            }
	        }
	        return node;
	    }
	    public void init(){
	        pow[0] = 1;
	        for (int i = 1; i < 17; i++) pow[i] = 2*pow[i-1];
	    }
	}
}
