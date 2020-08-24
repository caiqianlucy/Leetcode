/* author@ Qian Cai
 * Title@ Linked List Random Node
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? 
Could you solve this efficiently without using extra space?
 * 
 */
import java.util.*;
public class LeetCode382 {
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		 }
	class Solution1 {
	    /** @param head The linked list's head.
	        Note that the head is guaranteed to be not null, so it contains at least one node. */
	    Random rand;
	    int size = 0;
	    ListNode head;
	    public Solution1(ListNode head) {
	        rand = new Random();
	        this.head = head;
	        ListNode node = head;
	        while (node != null){
	            size++;
	            node = node.next;
	        }
	        //System.out.println(size);
	    }
	    
	    /** Returns a random node's value. */
	    public int getRandom() {
	        //System.out.println(size);
	        int idx = rand.nextInt(size);
	        ListNode cur = head;
	        while (idx-- > 0){
	            cur = cur.next;
	        }
	        return cur.val;
	    }
	}
	//Follow up
	class Solution2 {

	    /** @param head The linked list's head.
	        Note that the head is guaranteed to be not null, so it contains at least one node. */
	    ListNode head;
	    public Solution2(ListNode head) {
	        this.head = head;
	    }
	    
	    /** Returns a random node's value. */
	    public int getRandom() {
	        ListNode cur = head;
	        int len = 1;
	        int res = cur.val;
	        while (cur.next!=null){
	            len++;
	            cur = cur.next;
	            double rand = Math.random();
	            if (rand <= (1.0/len)) res = cur.val;
	        }
	        return res;
	    }
	}
}
