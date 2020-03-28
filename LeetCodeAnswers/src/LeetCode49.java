import java.util.*;

//Time: O(n)
public class LeetCode49 {
	public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str: strs){
            String sorted = sorted(str);
            if (!map.containsKey(sorted)) map.put(sorted, new ArrayList());
            map.get(sorted).add(str);
        }
        return new ArrayList(map.values());
        
    }
    public String sorted(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
        
    }
}
