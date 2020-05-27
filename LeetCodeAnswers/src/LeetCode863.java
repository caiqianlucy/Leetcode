/* author@ Qian Cai
 * Title@ All Nodes Distance K in Binary Tree
 * We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * 
 */
import java.util.*;
public class LeetCode863 {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parentMap = new HashMap();
        Set<TreeNode> visited = new HashSet();
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        //dfs to annotate parent node
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur.left != null){
                parentMap.put(cur.left, cur);
                stack.push(cur.left);
            } 
            if (cur.right != null){
                parentMap.put(cur.right, cur);
                stack.push(cur.right);
            }
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(target);
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (level == K){
                    res.add(node.val);
                }
                visited.add(node);
                if (node.left != null && !visited.contains(node.left)){
                    queue.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)){
                    queue.offer(node.right);
                }
                if (node != root && !visited.contains(parentMap.get(node))){
                    queue.offer(parentMap.get(node));
                }
            }
            level++;
            if (level == K+1) break;
        }
        return res;
    }
	public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
     }
}
