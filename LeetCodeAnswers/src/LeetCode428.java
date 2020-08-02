/* author@ Qian Cai
 * Title@ Serialize and Deserialize N-ary Tree
 * Time: O(n) dfs with child size
 * Space: O(n)
 * 
 */
import java.util.*;
public class LeetCode428 {
	 public String serialize(Node root) {
	        StringBuilder sb = new StringBuilder();
	        helper(root, sb);
	        return sb.toString();
	    }
	    public void helper(Node root, StringBuilder sb){
	        if (root == null) return;
	        sb.append((char)(root.val + '0'));
	        sb.append((char)(root.children.size() + '0'));
	        for (Node child: root.children){
	            helper(child, sb);
	        }
	    } 
		
	    // Decodes your encoded data to tree.
	    public Node deserialize(String data) {
	        if (data.isEmpty()) return null;
	        return dHelper(data, new WrappableInt(0));
	    }
	   
	    public Node dHelper(String data, WrappableInt index){
	         if (index.getValue() == data.length()) return null;
	         Node node = new Node(data.charAt(index.getValue())-'0', new ArrayList<Node>());
	        index.increment();
	        int c = data.charAt(index.getValue())-'0';
	        for (int i = 0; i < c; i++){
	            index.increment();
	            node.children.add(dHelper(data, index));
	        }
	        return node;
	            
	        }
	    
	    //a wrapper integer class, stateless requirement
	    class WrappableInt{
	        int value;
	        public WrappableInt(int x){
	            this.value = x;
	        }
	        public int getValue(){
	            return this.value;
	        }
	        public void increment(){
	            this.value++;
	        }
	    }
	    class Node {
	        public int val;
	        public List<Node> children;

	        public Node() {}

	        public Node(int _val) {
	            val = _val;
	        }

	        public Node(int _val, List<Node> _children) {
	            val = _val;
	            children = _children;
	        }
	    };

}
