/* author@ Qian Cai
 * Title@ Move Sub-Tree of N-Ary Tree
 * Given the root of an N-ary tree of unique values, and two nodes of the tree p and q.

You should move the subtree of the node p to become a direct child of node q. If p is already a direct child of q, don't change anything. Node p must be the last child in the children list of node q.

Return the root of the tree after adjusting it.

 

There are 3 cases for nodes p and q:

Node q is in the sub-tree of node p.
Node p is in the sub-tree of node q.
Neither node p is in the sub-tree of node q nor node q is in the sub-tree of node p.
In cases 2 and 3, you just need to move p (with its sub-tree) to be a child of q, 
but in case 1 the tree may be disconnected, thus you need to reconnect the tree again. 
Please read the examples carefully before solving this problem.
 */
import java.util.*;
public class LeetCode1516 {
	 public Node moveSubTree(Node root, Node p, Node q) {
	        Node pp = findPar(root, p), qp = findPar(root, q); 
	        //System.out.println(pp.val);
	         // System.out.println(qp.val);
	        if (pp == q) return root;
	        boolean qIsSubTreeP = isSubtree(q, p);
	        
	        if (!qIsSubTreeP){
	            pp.children.remove(p);
	            q.children.add(p);
	           
	        } else {
	            qp.children.remove(q);
	            q.children.add(p);
	            if (p != root) {
	                int idx = -1;
	                for (int i = 0; i < pp.children.size(); i++){
	                    if (pp.children.get(i) == p){
	                        idx = i;
	                        break;
	                    }
	                }
	                pp.children.set(idx, q);
	            }
	            else return q;
	        }
	        return root;
	    }
	    public Node findPar(Node root, Node p){
	        if (p == root) return null;
	        Queue<Node> queue = new LinkedList();
	        queue.add(root);
	        while (!queue.isEmpty()){
	            int size = queue.size();
	            for (int i = 0; i < size; i++){
	                Node cur = queue.poll();
	                for (Node child: cur.children){
	                    if (child == p) return cur;
	                    queue.add(child);
	                }
	            }
	        }
	        return null;
	    }
	    public boolean isSubtree(Node p, Node q){
	        if (q == null) return false;
	        for (Node child: q.children){
	            if (child == p) return true;
	            if (isSubtree(p, child)) return true;
	        }
	        return false;
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
	    };
}
