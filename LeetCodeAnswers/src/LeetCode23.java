
public class LeetCode23 {
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return helper(lists);
    }
    public ListNode helper(ListNode[] lists){
         int n = lists.length;
        if (n == 1) return lists[0];
        else if (n == 2){
            return mergeTwo(lists[0], lists[1]);
        } else {
           
            ListNode[] l1 = new ListNode[n/2];
            ListNode[] l2 = new ListNode[n-n/2];
            int k = 0;
            for (int i = 0; i < n/2; i++) l1[i] = lists[k++];
            for (int i = 0; i < n-n/2; i++) l2[i] = lists[k++];
            return mergeTwo(helper(l1), helper(l2));
        }
    }
    public ListNode mergeTwo(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode c1 = l1, c2 = l2, c = dummy;
        while (c1 != null && c2 != null){
            int v1 = c1.val;
            int v2 = c2.val;
            if (v1 < v2){
                c.next = new ListNode(v1);
                c1 = c1.next;
            } else{
                c.next = new ListNode(v2);
                c2 = c2.next;
            }
            c = c.next;
        }
        if (c1 != null) c.next = c1;
        if (c2 != null) c.next = c2;
        return dummy.next;
    }
    public class ListNode {
    	    int val;
    	    ListNode next;
    	    ListNode(int x) { val = x; }
    }
}
