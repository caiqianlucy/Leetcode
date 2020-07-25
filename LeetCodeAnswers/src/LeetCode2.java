/* author@ Qian Cai
 * title@ Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 */
public class LeetCode2 {
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode dummy = new ListNode(0);
	        ListNode cur = dummy;
	        int carry = 0;
	        ListNode c1 = l1, c2 = l2;
	        while (c1 != null || c2 != null){
	            int res = carry + (c1 != null ? c1.val: 0) + (c2 != null ? c2.val: 0);
	            cur.next = new ListNode(res%10);
	            carry = res/10;
	            cur = cur.next;
	            if (c1 != null) c1 = c1.next;
	            if (c2 != null) c2 = c2.next;
	        }
	        if (carry != 0) cur.next = new ListNode(carry);
	        return dummy.next;
	    }
	 public class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 }
}
