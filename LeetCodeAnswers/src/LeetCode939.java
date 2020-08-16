/* author@ Qian Cai
 * Title@ Minimum Area Rectangle 
 * 
 */
import java.util.*;
public class LeetCode939 {
	//without sorting
	class Solution1 {
	    /*time: O(n^2) space: O(n)
	    (m^2)*(k^2), m is unique x counts, k is common y counts in every two x, mk <= n
	    */
	    public int minAreaRect(int[][] points) {
	        Map<Integer, Set<Integer>>  xToY = new HashMap();
	        for (int[] point: points){
	            int x = point[0], y = point[1];
	            if (!xToY.containsKey(x)) xToY.put(x, new HashSet());
	            xToY.get(x).add(y);
	        }
	        int res  = Integer.MAX_VALUE;
	        for (int x1: xToY.keySet()){
	            for (int x2: xToY.keySet()){
	                if (x1 != x2){
	                    List<Integer> commons = getCommonY(xToY.get(x1), xToY.get(x2));
	                    if (commons.size() < 2) continue;
	                    for (int y1: commons){
	                        for (int y2: commons){
	                            if (y1 != y2){
	                                res = Math.min(res, Math.abs((y1-y2)*(x1-x2)));
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        return res == Integer.MAX_VALUE ? 0: res;
	    }
	    public List<Integer> getCommonY(Set<Integer> set1, Set<Integer> set2){
	        List<Integer> res = new ArrayList();
	        for (int y1: set1){
	            if (set2.contains(y1)) res.add(y1);
	        }
	        return res;
	    }
	}
	class Solution2 {
	    public int minAreaRect(int[][] points) {
	        Map<Integer, Set<Integer>>  tm = new TreeMap();
		    for (int[] point: points){
		        int x = point[0], y = point[1];
		        if (!tm.containsKey(x)) tm.put(x, new HashSet());
		        tm.get(x).add(y);
		    }
	        int res = Integer.MAX_VALUE;
	        Map<String, Integer> lastX = new HashMap(); //last x for (y1, y2)
	        for (int x: tm.keySet()){
	            Set<Integer> ys = tm.get(x);
	            if (ys.size() < 2) continue;
	            List<Integer> list = new ArrayList();
	            for (int y: ys) list.add(y);
	            Collections.sort(list);
	            for (int i = 0; i < list.size(); i++){
	                for (int j = i+1; j < list.size(); j++){
	                    int y1 = list.get(i), y2 = list.get(j);
	                    String key = y1 + "," + y2;
	                    if (lastX.containsKey(key)){
	                        res = Math.min((x-lastX.get(key))*(y2-y1), res);
	                    }
	                    lastX.put(key, x);
	                }
	            }
	        }
	        return res == Integer.MAX_VALUE ? 0: res;
	    }
	}
}
