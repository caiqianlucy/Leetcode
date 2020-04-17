/* author@ Qian Cai
 * Title@ Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object
 * into a sequence of bits so that it can be stored in a file or memory 
 * buffer, or transmitted across a network connection link to be reconstructed 
 * later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no 
restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and
 this string can be deserialized to the original tree structure.
 * Preorder traversal, time, space O(n)
 */
import java.util.*;
public class LeetCode297 {
	public String serialize(TreeNode root) {
        return recursiveSer(root, "");
    }
    public String recursiveSer(TreeNode node, String s){
        if (node == null) return s+"null,";
        s += String.valueOf(node.val) + ",";
        s = recursiveSer(node.left, s);
        s = recursiveSer(node.right, s);
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(array));
        return recursiveDeserialize(list);
    }
    public TreeNode recursiveDeserialize(List<String> list){
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        node.left = recursiveDeserialize(list);
        node.right = recursiveDeserialize(list);
        return node;
    }
    public class TreeNode {
    	     int val;
    	     TreeNode left;
    	     TreeNode right;
    	     TreeNode(int x) { val = x; }
    }
}
