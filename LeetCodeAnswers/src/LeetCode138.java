/*author@ Qian Cai
 * Title@ Copy List With Random Pointer
 * A linked list is given such that each node contains an additional random 
 * pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * The Linked List is represented in the input/output as a list of n nodes. 
 * Each node is represented as a pair of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random 
 * pointer points to, or null if it does not point to any node.
 * 
 * Solution: HashMap to record visited node, key is old node, value is new node
 * Time: O(n) Space: O(n)
 */
import java.util.Map;
import java.util.HashMap;
public class LeetCode138 {
	Map<Node, Node> map = new HashMap();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node old = head;
        Node newNode = new Node(old.val, null, null);
        map.put(old, newNode);
        while (old != null){
            newNode.random = this.getCloneNode(old.random);
            newNode.next = this.getCloneNode(old.next);
            old = old.next;
            newNode = newNode.next;
        }
        return this.map.get(head);
        
    }
    public Node getCloneNode(Node node){
        if (node != null){
            if (this.map.containsKey(node)) return this.map.get(node);
            else {
                this.map.put(node, new Node(node.val, null, null));
                return this.map.get(node);
            }
        }
        return null;
    }
	
	class Node {
	    public int val;
	    public Node next;
	    public Node random;

	    public Node() {}

	    public Node(int _val,Node _next,Node _random) {
	        val = _val;
	        next = _next;
	        random = _random;
	    }
	}
}
