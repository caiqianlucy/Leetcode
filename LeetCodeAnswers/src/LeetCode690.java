/* author@ Qian Cai
 * Title@ Employee Importance
 * 
 */
import java.util.*;
public class LeetCode690 {
	class Employee {
	    public int id;
	    public int importance;
	    public List<Integer> subordinates;
	};
	

	class Solution {
	    Map<Integer, Employee> map;
	    public int getImportance(List<Employee> employees, int id) {
	        map = new HashMap();
	        for (Employee e: employees){
	            map.put(e.id, e);
	        }
	        return dfs(map.get(id));
	    }
	    public int dfs(Employee e){
	        int ans = e.importance;
	        for (Integer subid: e.subordinates){
	            ans += dfs(map.get(subid));
	        }
	        return ans;
	    }
	}
}
