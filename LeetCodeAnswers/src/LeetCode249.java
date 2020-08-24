/* author@ Qian Cai
 * Title@ Group Shifted Strings
 * 
 */
import java.util.*;
public class LeetCode249 {
	 public List<List<String>> groupStrings(String[] strings) {
	        List<List<String>> res = new ArrayList();
	        Map<String, List<String>> map = new HashMap();
	        for (String s: strings){
	            String cs = shift(s);
	            if (!map.containsKey(cs)) map.put(cs, new ArrayList());
	            map.get(cs).add(s);
	        }
	        for (String key: map.keySet()){
	            res.add(new ArrayList<String>(map.get(key)));
	        }
	        return res;
	    }
	    public String shift(String s){
	        int delta = s.charAt(0) - 'a';
	        StringBuilder sb = new StringBuilder();
	        sb.append('a');
	        for (int i = 1; i < s.length(); i++){
	            char cur = (char)((s.charAt(i) + 26 - delta)%26 + 'a');
	            sb.append(cur);
	        }
	        return sb.toString();
	        
	    }
}
