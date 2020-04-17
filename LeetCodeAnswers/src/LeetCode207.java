/* author@ Qian Cai
 * Title@ Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to
 *  numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to 
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it 
possible for you to finish all courses?
 * 
 * Topological Sort 
 * Time, Space complexity: O(E+V)
 */
import java.util.*;
public class LeetCode207 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] adjList = new List[numCourses];
        for (int i = 0; i < numCourses; i++) adjList[i] = new ArrayList();
        for (int[] pair: prerequisites){
            int from = pair[1], to = pair[0];
            indegree[to]++;
            //System.out.println("from " +from + " to " + to);
            adjList[from].add(to);
        }
        int count = 0; //count of course taken
        
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] == 0){
                count++;
                queue.add(i);
                //System.out.println(i);
            }
        }
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (int nei: adjList[cur]){
                System.out.println(nei);
                indegree[nei]--;
                if (indegree[nei] == 0){
                    count++;
                    queue.add(nei);
                }
            }
        }
        return count == numCourses;
    }
}
