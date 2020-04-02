import java.util.*;
/*author@ Qian Cai
 * Title@ Most Frequent Subtree Sum
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 * Time, Space: O(n)
 */
public class LeetCode508 {
	Map<Integer, Integer> map; //subtree sum and frequency
    public int[] findFrequentTreeSum(TreeNode root) {
        map = new HashMap();
        backtrack(root);
        int max = Integer.MIN_VALUE;
        for (int val: map.values()){
            if (val > max) max = val;
        }
        List<Integer> list = new ArrayList();
        for (int key: map.keySet()){
            if (map.get(key) == max) list.add(key);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }
    public int backtrack(TreeNode node){
        if (node == null) return 0;
        int sum = node.val;
        if (node.left != null || node.right != null) {
            sum += backtrack(node.left) + backtrack(node.right);      
        }
        map.put(sum, map.getOrDefault(sum, 0)+1);
        return sum;
    }
    public class TreeNode {
      	 int val;
      	 TreeNode left;
      	 TreeNode right;
      	 TreeNode(int x) { val = x; }
      }
}
