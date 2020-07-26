/* author@ Qian Cai
 * Title@ Number of Good Leaf Nodes Pairs
 * solution 1: time: O(n) dfs
 * Solution 2: time: O(2^10*n) bfs
 * 
 */
import java.util.*;
public class LeetCode1530 {
	class Solution1 {
	    public int countPairs(TreeNode root, int distance) {
	        return helper(root, distance)[11];
	    }
	    //0-10 store count of distance 0-10 nodes counts, 11 store total valid pairs
	    public int[] helper(TreeNode node, int distance){
	        int[] res = new int[12];
	        if (node == null) return res;
	        if (node.left == null && node.right == null){
	            res[1] = 1;
	            return res;
	        }
	        int[] left = helper(node.left, distance);
	        int[] right = helper(node.right, distance);
	        for (int i = 0; i <= 10; i++){
	            for (int j = 0; j <= 10; j++){
	                if (i + j <= distance) {
	                    res[11] += left[i]*right[j];
	                }
	            }
	        }
	        res[11] += left[11] + right[11];
	        for (int i = 0; i <= 9; i++){
	            res[i+1] += left[i];
	            res[i+1] += right[i];
	        }
	        //System.out.println(node.val);
	        //System.out.println(res[11]);
	        return res;
	    }
	}	
	class Solution2 {
	    public int countPairs(TreeNode root, int distance) {
	        Map<TreeNode, TreeNode> par = new HashMap();
	        Set<TreeNode> leafs = new HashSet();
	        build(par, leafs, root);
	        int res = 0;
	        Set<TreeNode> visited = new HashSet();
	        for (TreeNode node: leafs){
	            int pairs = bfs(distance, node, par, leafs, visited);
	            res += pairs;
	        }
	        return res;
	    }
	    public int bfs(int distance, TreeNode node, Map<TreeNode, TreeNode> par, Set<TreeNode> leafs, Set<TreeNode> visited){
	        int step = 1;
	        Queue<TreeNode> queue = new LinkedList();
	        Set<TreeNode> added = new HashSet();
	        queue.add(node);
	        added.add(node);
	        int res = 0;
	        while (!queue.isEmpty() && step <= distance){
	            int size = queue.size();
	            for (int i = 0; i < size; i++){
	                TreeNode cur = queue.poll();
	                if (cur.left != null && !added.contains(cur.left)){
	                    if (leafs.contains(cur.left) && !visited.contains(cur.left)){
	                        res++;
	                    } else if (!leafs.contains(cur.left)){
	                        queue.add(cur.left);
	                        added.add(cur.left);
	                    }
	                }
	                if (cur.right != null && !added.contains(cur.right)){
	                    if (leafs.contains(cur.right) && !visited.contains(cur.right)){
	                        res++;
	                    } else if (!leafs.contains(cur.right)){
	                        queue.add(cur.right);
	                        added.add(cur.right);
	                    }
	                }
	                if (par.containsKey(cur) && !added.contains(par.get(cur))){                  
	                        queue.add(par.get(cur));
	                        added.add(par.get(cur));
	                }
	            }
	            step++;
	        }
	        if (res > 0) visited.add(node);
	        return res;
	    }
	    public void build(Map<TreeNode, TreeNode> par, Set<TreeNode> leafs, TreeNode node){
	        if (node.left == null && node.right == null) leafs.add(node);
	        if (node.left != null){
	            par.put(node.left, node);
	            build(par, leafs, node.left);
	        }
	        if (node.right != null){
	            par.put(node.right, node);
	            build(par, leafs, node.right);
	        }
	    }
	}
	public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode() {}
		     TreeNode(int val) { this.val = val; }
		     TreeNode(int val, TreeNode left, TreeNode right) {
		         this.val = val;
		         this.left = left;
		         this.right = right;
		     }
		  }
}
