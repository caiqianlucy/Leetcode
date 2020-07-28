/* author@ Qian Cai
 * Title@ Course Schedule III
 * There are n different online courses numbered from 1 to n. Each course has some 
 * duration(course length) t and closed on dth day. A course should be taken continuously for 
 * t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of
 courses that can be taken.
 * 
 */
import java.util.*;
public class LeetCode630 {
	//time: O(nlogn)
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b)->(a[1]-b[1]));
        int time = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b)->(b-a));
        int count = 0;
        for (int[] course: courses){
            if (time + course[0] <=course[1]){
                count++;
                time+= course[0];
                pq.add(course[0]);
            } else {
                
                if (!pq.isEmpty() && course[0] < pq.peek()){
                    time += course[0]-pq.poll();
                    pq.add(course[0]);
                }
                
            }
        }
        return count;
    }
}
