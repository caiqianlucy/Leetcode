/* author@ Qian Cai
 * Title@ Parellel Courses II
 * Given the integer n representing the number of courses at some university labeled from 1 to n, 
 * and the array dependencies where dependencies[i] = [xi, yi]  represents a prerequisite
 *  relationship, that is, the course xi must be taken before the course yi.  
 *  Also, you are given the integer k.

In one semester you can take at most k courses as long as you have taken all the prerequisites 
for the courses you are taking.

Return the minimum number of semesters to take all courses. It is guaranteed that you can take
 all courses in some way.
 * 
 */
import java.util.*;
public class LeetCode1494 {
	public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] indegree = new int[n+1];
        int[] outdegree = new int[n+1];
        if (dependencies.length == 0) return n/k + (n%k == 0 ? 0: 1);
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int[] dep: dependencies){
            int from = dep[0], to = dep[1];
            if (!graph.containsKey(from)) graph.put(from, new ArrayList());
            graph.get(from).add(to);
            indegree[to]++;
            outdegree[from]++;
        }
        Set<Integer> visited = new HashSet();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b)->(outdegree[b]-outdegree[a]));
        int res = 0;
        for (int i = 1; i <= n; i++){
            if (indegree[i] == 0){
                //System.out.println(i);
                visited.add(i);
                queue.add(i);
            }
        }
        /* bfs 
        each semester can take k course,
        if course can be taken is < k need one semester to take all course
        if course is > k, need count = size/k semester to take count*k courses
        the rest 
        */
        while (!queue.isEmpty()){
            int size = queue.size();
            //System.out.println(size);
            res++;
            List<Integer> temp = new ArrayList();
            for (int i = 0; i < Math.min(k, size); i++){              
                int cur = queue.poll();
                helper(visited, graph, indegree, temp, cur);
            }
            for (int next: temp) queue.add(next); 
        }
        return res;
    }
    public void helper(Set<Integer> visited, Map<Integer, List<Integer>> graph, int[] indegree, List<Integer> temp, int cur){
        if (!graph.containsKey(cur)) return;
        for (int nei: graph.get(cur)){
                    indegree[nei]--;
                    if (indegree[nei] == 0 && !visited.contains(nei)){
                        temp.add(nei);
                        visited.add(nei);
                    }
        }
    }
}
