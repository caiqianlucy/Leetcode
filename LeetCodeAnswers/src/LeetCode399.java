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
	class Solution2 {
	    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
	        UnionFind uf = new UnionFind(equations, values);
			int n = queries.size();
			double[] res = new double[n];
			for (int i = 0; i < n; i++){
				String x = queries.get(i).get(0), y = queries.get(i).get(1);
				res[i] = uf.getRatio(x, y);
	            //System.out.println(x + " / " + y + " res: " + res[i]);
			}
			return res;
	    }
	private class UnionFind{
		Map<String, Pair> parents;
		public UnionFind(List<List<String>> equations, double[] values){
			parents = new HashMap();
			for (int i = 0; i < values.length; i++){
				String x = equations.get(i).get(0), y = equations.get(i).get(1);
				if (!parents.containsKey(x) && !parents.containsKey(y)){
					parents.put(x, new Pair(y, values[i]));
					parents.put(y, new Pair(y, 1.0));
				} else if (!parents.containsKey(x)){
					parents.put(x, new Pair(y, values[i]));
				} else if (!parents.containsKey(y)){
					parents.put(y, new Pair(x, 1.0/values[i]));
				} else union(x, y, values[i]);
				}
		}
		public void union(String x, String y, double val){
	// x/pX = pX.val, y/pY = pY.val, x/y = val, pX/pY = pY.val*val/pX.val;
			Pair pX = find(x), pY = find(y);
			if (!pX.s.equals(pY.s)){
				parents.put(pX.s, new Pair(pY.s, pY.val*val/pX.val));
			}
		}
		public Pair find(String x){
			Pair xp = parents.get(x);
	        //System.out.println(xp.s);
			if (!xp.s.equals(x)){
				parents.put(x, new Pair(find(xp.s).s, xp.val*find(xp.s).val)); 
			}
			return parents.get(x);
		}
		public double getRatio(String x, String y){
	        //System.out.println(x + parents.containsKey(x));
	        //System.out.println(y + parents.containsKey(y));
	        if (!parents.containsKey(x) || !parents.containsKey(y)) return -1;

			Pair xp = find(x), yp = find(y);
	        //System.out.println(xp.s + " " + xp.val);
	        //System.out.println(yp.s + " " + yp.val);
			if (xp.s.equals(yp.s)) return xp.val/yp.val;
			return -1;
		}
	}
	private class Pair{
		String s;
		double val; //ratio of key/s
		Pair(String s, double val){
			this.s = s;
			this.val = val;
		}
	}
}
}
