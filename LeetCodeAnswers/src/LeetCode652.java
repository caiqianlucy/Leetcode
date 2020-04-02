import java.util.*;
/*author@ Qian Cai
 * Title@ Find Duplicate Subtrees
 * Longest Substring with At Most K Distinct Characters
 * Time: O(n) Space: O(n)
 */
public class LeetCode652 {
	int id; //give each node an id
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        id = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        helper(root);
        return ans;
    }
    //return the id of the node
    public int helper(TreeNode node){
        if (node == null) return 0;
        String serial = node.val + "," + helper(node.left) + "," + helper(node.right);
        if (!trees.containsKey(serial)) trees.put(serial, id++);
        int cur = trees.get(serial);
        count.put(cur, count.getOrDefault(cur, 0) + 1);
        if (count.get(cur) == 2){
            ans.add(node);
        }
        return cur;
    }
    public class TreeNode {
     	 int val;
     	 TreeNode left;
     	 TreeNode right;
     	 TreeNode(int x) { val = x; }
     }
}
