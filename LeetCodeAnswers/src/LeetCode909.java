/* author@ Qian Cai
 * Title@ Snakes and Ladders
 * https://leetcode.com/problems/snakes-and-ladders/
 * //BFS, time: O(n*n) space: O(n*n)
 * 
 */
import java.util.*;
public class LeetCode909 {
	public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList();
        boolean[] visited = new boolean[n*n+1];
        int dist = 0;
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                if (cur == n*n) return dist;
                for (int next = cur+1; next <= Math.min(cur+6, n*n); next++){
                    int[] rc = getIdx(next, n);
                    int r = rc[0], c = rc[1];
                    int finalPos = board[r][c] == -1? next: board[r][c];
                    if (!visited[finalPos]){
                        queue.add(finalPos);
                        visited[finalPos] = true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }
    public int[] getIdx(int i, int n){
        int rowFromLast = (i-1)/n;
        int colFromBegin = (i-1)%n;
        int[] res = new int[2];
        res[0] = n-1-rowFromLast;
        res[1] = (rowFromLast % 2 == 0) ? colFromBegin: (n-1-colFromBegin);
        return res;
    }
}