/* author@ Qian Cai
 * Title@ Insert Delete GetRandom
 * Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of 
elements. Each element must have the same probability 
of being returned.
 * 
 * Solution:
 * HashMap + ArrayList
 */
import java.util.*;
public class LeetCode380 {
	class RandomizedSet {
	     Map<Integer, Integer> map;
	     List<Integer> list;
	    Random rand;
	    /** Initialize your data structure here. */
	    public RandomizedSet() {
	        map = new HashMap();
	        list = new ArrayList();
	        rand = new Random();
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	        if (map.containsKey(val)) return false;
	        map.put(val, list.size());
	        list.add(val);
	        return true;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        if (!map.containsKey(val)) return false;
	        
	            int idx = map.get(val);
	            int last = list.get(list.size()-1);
	            list.set(idx, last);
	            map.put(last, idx);
	            map.remove(val);
	        list.remove(list.size()-1);
	        return true;
	        
	    }
	    
	    /** Get a random element from the set. */
	    public int getRandom() {
	        return list.get(rand.nextInt(list.size()));
	    }
	}
}
