/* author@ Qian Cai
 * Title@ Design HashMap
 * Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. 
If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, 
or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the 
mapping for the key.
 * 
 */
import java.util.*;
class MyHashMap {
    private int key_space;
    private List<Bucket> hash_table;
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        this.key_space = 10000;
        this.hash_table = new ArrayList<Bucket>();
        for (int i = 0; i < key_space; i++) hash_table.add(new Bucket());
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash_key = key%key_space;
        hash_table.get(hash_key).update(key, value);
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash_key = key%key_space;
        return hash_table.get(hash_key).get(key);
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash_key = key%key_space;
        hash_table.get(hash_key).remove(key);
    }
    class Bucket{
        private List<int[]> bucket;
        public Bucket(){
            bucket = new LinkedList<int[]>();
        }
        public int get(int key){
            for (int[] pair: bucket){
                //System.out.println("key is " + pair[0] + " value is " + pair[1]);
                if (pair[0] == key){
                    return pair[1];
                }
            }
            return -1;
        }
        public void update(int key, int value){
            boolean found = false;
            for (int[] pair: bucket){
                if (pair[0] == key){
                    pair[1] = value;
                    found = true;
                }
            }
            
            if (!found) bucket.add(new int[]{key, value});
            
        }
        public void remove(int key){
            for (int[] pair: bucket){
                if (pair[0] == key){
                    bucket.remove(pair);
                    break;
                }
            }
        }
    }
}
