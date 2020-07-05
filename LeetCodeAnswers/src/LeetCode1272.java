/* author@ Qian Cai
 * Title@ Remove Intervals
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.

We remove the intersections between any interval in intervals and the interval toBeRemoved.

Return a sorted list of intervals after all such removals.

 

Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]

time: O(n)

Four scenario for each intervals
1. no intersection with toBeRemoved,
2. toBeRemoved in included
3. left Intersection with toBeRemoved
4. right Intersection with toBeRemoved
 */
import java.util.*;
public class LeetCode1272 {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList();
        int start = toBeRemoved[0], end = toBeRemoved[1];
        for (int i = 0; i < intervals.length; i++){
            int cStart = intervals[i][0], cEnd = intervals[i][1];
            if (cStart >= end || cEnd <= start){                
                res.add(listHelper(cStart, cEnd)); //no intersection
            } else {
                if (cStart < start && cEnd > end){  // toBeRemoved in included
                    res.add(listHelper(cStart, start));
                    res.add(listHelper(end, cEnd));
                } else {
                    if (cStart < start && cEnd <= end){
                         res.add(listHelper(cStart, start)); //left Intersection
                    } else if (cStart >= start && cEnd > end){
                         res.add(listHelper(end, cEnd)); //right Intersection
                    }
                }
            }
        }
        
        return res;
    }
    public List<Integer> listHelper(int s, int e){
        List<Integer> list = new ArrayList();
        list.add(s);
        list.add(e);
        return list;
    }
}
