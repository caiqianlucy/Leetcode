import java.util.*;
//PriorityQueue time: O(nlogn)

public class LeetCode218 {
	public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> height = new ArrayList();
        List<List<Integer>> res = new ArrayList();
        for (int[] b: buildings){
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b)->{
            return a[0] == b[0] ? a[1]-b[1]:a[0]-b[0];
        });
        Queue<Integer> pq = new PriorityQueue<Integer>((a, b)->(b-a));
        pq.offer(0);
        int prev = 0;
        for (int[] h: height){
            if (h[1] < 0){
                pq.offer(-h[1]); //for new building offer the height
            } else pq.remove(h[1]); //for old building remove the height
            int cur = pq.peek();
            if (prev != cur){
                List<Integer> list = new ArrayList();
                list.add(h[0]);
                list.add(cur);
                res.add(list);
                prev = cur;
            }
        }
        return res;
    }
}
