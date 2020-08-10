/* author@ Qian Cai
 * Title@ Evaluate Division
 * time: O(E + V) space: O(E+V)
 * Solution 1: DfS
 * Solution 2: Union Find
 */
import java.util.*;
public class LeetCode399 {
	class Solution1 {
	    //dfs
	    Map<String, Map<String, Double>> edges;
	    Map<String, String> root;
	       Map<String, Double> val;
	     Set<String> visited;
	    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
	        edges = new HashMap();
	        root = new HashMap(); //root(string)
	        val = new HashMap(); //val: p(key)/key
	        visited = new HashSet();
	        for (int i = 0; i < values.length; i++){
	            String a = equations.get(i).get(0), b = equations.get(i).get(1);
	            double v = values[i];
	            if (!edges.containsKey(a)) edges.put(a, new HashMap());
	            if (!edges.containsKey(b)) edges.put(b, new HashMap());
	            edges.get(a).put(b, v);
	            edges.get(b).put(a, 1.0/v);
	        }
	        for (String key: edges.keySet()){
	            if (!visited.contains(key)){
	                dfs(key, key, 1.0);
	            }
	        }
	        int n = queries.size();
	        double[] res = new double[n];
	        for (int i = 0; i < n; i++){
	            String a = queries.get(i).get(0), b = queries.get(i).get(1);
	            if (root.getOrDefault(a, a) != root.getOrDefault(b, b)) res[i] = -1.0;
	            else{
	                if (!val.containsKey(a)) res[i] = 1.0;
	                else res[i] = val.get(a)/val.get(b);
	            }
	        }
	        return res;
	    }
	    public void dfs(String a, String p, double v){
	        root.put(a, p);
	        val.put(a, v);
	        visited.add(a);
	        for (String next: edges.get(a).keySet()){
	            if (!visited.contains(next)){
	                dfs(next, p, v/edges.get(a).get(next));
	            }          
	        }
	    }
	}
}
