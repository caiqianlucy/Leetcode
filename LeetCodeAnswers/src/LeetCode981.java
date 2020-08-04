/* author@ Qian Cai
 * Title@ Time Based Key-Value Store
 * Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").
 * 
 */
import java.util.*;
public class LeetCode981 {
	//Time: O(lgn) for set and get, Space: O(n)
	class TimeMap {
	    Map<String, TreeMap<Integer, String>> map;
	    /** Initialize your data structure here. */
	    public TimeMap() {
	        map = new HashMap();
	    }
	    
	    public void set(String key, String value, int timestamp) {
	        if (!map.containsKey(key)) map.put(key, new TreeMap());
	        map.get(key).put(timestamp, value);
	    }
	    
	    public String get(String key, int timestamp) {
	        if (!map.containsKey(key)) return "";
	        Integer t = map.get(key).floorKey(timestamp);
	        return t != null ? map.get(key).get(t): "";
	    }
	}

}
