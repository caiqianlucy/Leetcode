import java.util.Arrays;
import java.util.PriorityQueue;

//Dijkstra's algorithm Time: O(ElogV)
public class LeetCode1102 {
	public int maximumMinimumPath(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MIN_VALUE);
        dist[0][0] = A[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->b[0]-a[0]);
        pq.offer(new int[]{A[0][0], 0, 0});
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int s = cur[0];
            int x = cur[1], y = cur[2];
            for (int d = 0; d < 4; d++){
                int nx = x + dir[d][0], ny = y + dir[d][1];
                if (isValid(nx, ny, m, n)){
                    int ns = Math.min(s, Math.min(A[x][y],A[nx][ny]));
                    if (ns > dist[nx][ny]){
                        dist[nx][ny] = ns;
                        pq.offer(new int[]{ns, nx, ny});
                    }
                }
            }
        }
        return dist[m-1][n-1];
    }
    private boolean isValid(int x, int y, int m, int n){
        return x >= 0 && x <  m && y >= 0 && y < n;
    }
}
