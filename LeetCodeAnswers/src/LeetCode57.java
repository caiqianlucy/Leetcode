/* author@ Qian Cai
 * Title@ Insert Interval
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.
 * 
 * greedy
 * time: O(n) space: O(n) for res
 */
import java.util.*;
public class LeetCode57 {
	public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        int newStart = newInterval[0], newEnd = newInterval[1];
        int i = 0;
        int n = intervals.length;
        while (i < n){
            if (newStart > intervals[i][1]){
            res.add(new int[]{intervals[i][0], intervals[i++][1]});
            } else if (intervals[i][0] > newEnd){
                res.add(new int[]{newStart, newEnd});
                break;
            } else {
                newStart = Math.min(intervals[i][0], newStart);
                newEnd = Math.max(intervals[i++][1], newEnd);
            }            
        } 
        while (i < n) res.add(new int[]{intervals[i][0], intervals[i++][1]});
        if (res.isEmpty() || res.get(res.size()-1)[1] < newStart) res.add(new int[]{newStart, newEnd});
        int[][] ans = new int[res.size()][2];
        for (int j = 0; j < res.size(); j++){
            ans[j][0] = res.get(j)[0];
            ans[j][1] = res.get(j)[1];
        }
        return ans;
    }
	//simpler version, add each region all once
	public int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        int newStart = newInterval[0], newEnd = newInterval[1];
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][1] < newStart){
            res.add(intervals[i++]);
        }
        while (i < n && intervals[i][0] <= newEnd){
            newStart = Math.min(intervals[i][0], newStart);
            newEnd =Math.max(intervals[i][1], newEnd);
            i++;
        }
        res.add(new int[]{newStart, newEnd});
        while (i < n){
            res.add(intervals[i++]);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
