/* author@ Qian Cai
 * Title@ Parellel Courses
 * There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and
 course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the 
prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study 
all the courses, return -1.
 * time: O(E+V) E is edge, V is vertices followup LeetCode1494
 */
import java.util.*;
public class LeetCode1136 {
	public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[N+1];
        for (int[] relation: relations){
            if (!graph.containsKey(relation[0])) graph.put(relation[0], new ArrayList());
            graph.get(relation[0]).add(relation[1]);
            indegree[relation[1]]++;
        }
        Queue<Integer> queue = new LinkedList();
        int courseTaken = 0;
        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0) queue.add(i);
        }
        int level = 0;
        while (!queue.isEmpty()){
             int size = queue.size();
             courseTaken += size;
            level++;
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                if (graph.containsKey(cur)){
                    for (int next: graph.get(cur)){
                       indegree[next]--;
                        if (indegree[next] == 0){
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return courseTaken == N ? level: -1;
    }
}
