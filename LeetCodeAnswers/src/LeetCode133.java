/* author@ Qian cai
 * Title@ Clone Graph
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). 
For example, the first node with val = 1, the second node with val = 2, and so on. 
The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph.
 Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of 
the given node as a reference to the cloned graph.
 * 
 */
import java.util.*;
class Solution {
    Map<Node, Node> map = new HashMap(); //keep all visited node
    public Node cloneGraph(Node node) {
        if (node == null ) return null;
        if (map.containsKey(node)) return map.get(node);
        Node newNode = new Node(node.val, new ArrayList());
        map.put(node, newNode);
        for (Node nei: node.neighbors){
            newNode.neighbors.add(cloneGraph(nei));
        }
        return newNode;     
    }
    class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
