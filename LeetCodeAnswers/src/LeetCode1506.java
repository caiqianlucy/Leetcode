/* author@ Qian Cai
 * Title@ Find Root of a N-ary Tree
 * Given all the nodes of an N-ary tree as an array  Node[] tree where each node has a unique value.

Find and return the root of the N-ary tree.
 * 
 */
import java.util.*;
public class LeetCode1506 {
	public Node findRoot(List<Node> tree) {
        Map<Node, Integer> indegree = new HashMap();
        for (Node node: tree){
            if (node == null) continue;
            if (!indegree.containsKey(node)) indegree.put(node, 0);
            for (Node child: node.children){
                indegree.put(child, 1);
            }
        }
        for (Node key: indegree.keySet()){
            if (indegree.get(key) == 0) return key;
        }
        return null;
    }
	class Node {
	    public int val;
	    public List<Node> children;

	    
	    public Node() {
	        children = new ArrayList<Node>();
	    }
	    
	    public Node(int _val) {
	        val = _val;
	        children = new ArrayList<Node>();
	    }
	    
	    public Node(int _val,ArrayList<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}
}
