import java.util.Arrays;
import java.util.PriorityQueue;

/* Two solutions:
 * 1. PriorityQueue solution Time: O(nlogK), space:O(K)
 * 2. quick select solution
 */
public class LeetCode973 {
	public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)-> (b[0]-a[0]));
        int[][] res = new int[K][2];
        for (int[] point: points){
            pq.add(new int[]{point[0]*point[0] + point[1]*point[1], point[0], point[1]});
            if (pq.size() > K){
                pq.poll();
            }
        }
        int i = 0;
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            res[i][0] = cur[1];
            res[i++][1] = cur[2];
        }
        return res;
    }
	public int[][] kClosestQuickSelect(int[][] points, int K) {
        int n = points.length;
        int l = 0, r = n-1;
        while (l <= r){
            int mid = l + (r-l)/2;
            int idx = partition(points, l, r, mid);
            if (idx == K-1) break;
            if (idx < K-1){
                l = idx+1;
            } else {
                r = idx-1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    public int partition(int[][] points, int l, int r, int pivotIdx){
        int[] pivot = points[pivotIdx];
        swap(points, pivotIdx, r);
        int cnt = l;      
        for (int i = l; i <= r; i++){
            if (dist(points[i]) < dist(pivot)){
                swap(points, i, cnt);
                cnt++;
            }
        }
        swap(points, cnt, r);
        return cnt;
    }
    public int dist(int[] point){
        return point[0]*point[0] + point[1]*point[1];
    }
    public void swap(int[][] points, int x, int y){
        int[] temp = points[x];
        points[x] = new int[]{points[y][0], points[y][1]};
        points[y] = new int[]{temp[0], temp[1]};
    }
}
