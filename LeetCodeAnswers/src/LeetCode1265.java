import java.util.Stack;

/*author@Qian Cai
 * Title@Print Immutable Linked List in Reverse
 *You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:
ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
ImmutableListNode.printValue(): Print value of the current node.
ImmutableListNode.getNext(): Return the next node.
The input is only given to initialize the linked list internally. You must solve this problem without modifying the linked list. In other words, you must operate the linked list using only the mentioned APIs.
 * 1.space: o(n) time: o(n)
2. FollowUp constant space, time o(n^2)
3. FollowUp less than linear space complexity, linear time complexity
 */
public class LeetCode1265 {
	interface ImmutableListNode {
		 public void printValue(); // print the value of this node.
         public ImmutableListNode getNext(); // return the next node.
	};
	public void printLinkedListInReverse1(ImmutableListNode head) {
        Stack<ImmutableListNode> stack = new Stack();
        while (head != null){
            stack.push(head);
            head = head.getNext();
        }
        while (!stack.isEmpty()){
            stack.pop().printValue();
        }
    }
     public void printLinkedListInReverse2(ImmutableListNode head) {
       int size = getCount(head);
       for (int i = size; i >= 1; i--){
           printNthNode(head, i);
       }
    }
    public int getCount(ImmutableListNode head){
       int size = 0;
       ImmutableListNode node = head;
       while (node != null){
           size++;
           node = node.getNext();
       }
        return size;
    }
    public void printNthNode(ImmutableListNode head, int idx){
        ImmutableListNode node = head;
        for (int i = 0; i < idx-1; i++) node = node.getNext();
        node.printValue();
    }
    //split the list into smaller part each part is Math.sqrt(size) space O(n^0.5)
     public void printLinkedListInReverse3(ImmutableListNode head) {
        Stack<ImmutableListNode> headStack = new Stack();
        int size = getCount(head);
        int each = (int)Math.sqrt(size);
        int i = 0;
        ImmutableListNode node = head;
        while (node != null){
           if (i % each == 0) headStack.push(node);
           node = node.getNext();
           i++;
        }
        while (!headStack.isEmpty()){
            ImmutableListNode cur = headStack.pop();
            Stack<ImmutableListNode> stack = new Stack();
            int n = 0;
            while (n < each && cur != null){
                stack.push(cur);
                cur = cur.getNext();
                n++;
            }
            while (!stack.isEmpty()){
                stack.pop().printValue();
            }
        }
    }
}
