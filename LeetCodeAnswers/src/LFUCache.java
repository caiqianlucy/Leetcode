import java.util.*;
/* author@ Qian Cai
 * Title@ 460.LFU Cache
 * Design and implement a data structure for Least 
 * Frequently Used (LFU) cache. It should support 
 * the following operations: get and put.

get(key) - Get the value (will always be positive) of 
the key if the key exists in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is 
not already present. When the cache reaches its capacity,
 it should invalidate the least frequently used item before
  inserting a new item. For the purpose of this problem,
   when there is a tie (i.e., two or more keys that have 
   the same frequency), the least recently used key would
    be evicted.

Note that the number of times an item is used is the 
number of calls to the get and put functions for that 
item since it was inserted. This number is set to zero 
when the item is removed.
 * Solution: Two HashMap + DoubleLinkedList
 */
public class LFUCache {
	private Map<Integer, Node> map = new HashMap();
    private Map<Integer, DList> count = new HashMap();
    private int size;
    private int capacity;
    private int min;
    
    public LFUCache(int capacity) {
       size = 0;
       this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        update(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            update(node);
        } else {
            Node newNode = new Node(key, value);	           	         map.put(key, newNode);	
            //System.out.println("size: " + size + " capacity: " + capacity);
            if (size == capacity){
                DList minList = count.get(min);
                map.remove(minList.popTail().key);
                --size;
            }
            size++;
            min = 1;
            DList list = count.getOrDefault(1, new DList());
            list.addNode(newNode);
            count.put(1, list);
        } 
  }
public void update(Node node){
    DList list = count.get(node.count);
    list.removeNode(node);
    if (node.count == min && list.size == 0) min++;
    node.count++;
    DList newList = count.getOrDefault(node.count, new DList());
    newList.addNode(node);
    count.put(node.count, newList);
}

class Node {
        int key;
        int value;
        int count;
        Node prev;
        Node next;
        Node(int key, int val){
            this.key = key;
            value = val;
            count = 1;
        }
}
class DList {
    Node head, tail;
    int size;
    DList(){
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    private void addNode(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        size++;
    }
    private void removeNode(Node node){
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }
    
    private Node popTail(){
        if (size <= 0) return null;
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}
}
