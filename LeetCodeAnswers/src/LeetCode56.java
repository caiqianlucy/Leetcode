import java.util.*;
/*author@ Qian Cai
 * Title@ Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * Time: O(nlogn), Space: O(1)
 */
public class LeetCode56 {
	public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList();
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return (a[0]-b[0]) == 0 ? (a[1]-b[1]):(a[0]-b[0]);
            }
        });
        if (intervals == null || intervals.length == 0) return new int[0][0];
        int start = intervals[0][0];
        int end = intervals[0][1];
        int idx = 1;
        int n = intervals.length;
        while (idx < n){
            if (end < intervals[idx][0]){
                list.add(new int[]{start, end});
                start = intervals[idx][0];
                end = intervals[idx][1];
            } else {
                end = Math.max(end, intervals[idx][1]);
            }
            idx++;
        }
        list.add(new int[]{start, end});
        int[][] res = new int[list.size()][2];
        int i = 0;
        for (int[] a: list){
            res[i][0] = a[0];
            res[i++][1] = a[1];
        }
        return res;
        
    }
}
