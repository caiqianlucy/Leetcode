/* author@ Qian Cai
 * Title@ pacific atlantic water flow
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 * time: O(mn) space: O(mn)
 */
import java.util.*;
public class LeetCode417 {
	int m, n;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new ArrayList();
        m = matrix.length;
        n = matrix[0].length;
        Queue<int[]> pacific = new LinkedList();
        Queue<int[]> atlantic = new LinkedList();
        Set<Integer> aSet = new HashSet();
        Set<Integer> pSet = new HashSet();
        for (int i = 0; i < m; i++){
            if (!pSet.contains(i*n)){
                pSet.add(i*n);
                pacific.add(new int[]{i, 0});
            }
            if (!aSet.contains(i*n + n-1)){
                aSet.add(i*n + n-1);
                atlantic.add(new int[]{i, n-1});
            }
        }
        for (int j = 0; j < n; j++){
            if (!pSet.contains(j)){
                pSet.add(j);
                pacific.add(new int[]{0, j});
            }
            if (!aSet.contains((m-1)*n + j)){
                aSet.add((m-1)*n + j);
                atlantic.add(new int[]{m-1, j});
            }
        }
        bfs(pSet, pacific, matrix);
        bfs(aSet, atlantic, matrix);
        List<List<Integer>> res = new ArrayList();
        for (int k: pSet){
            if (aSet.contains(k)){
                List<Integer> list = new ArrayList();
                list.add(k/n);
                list.add(k%n);
                res.add(list);
            }
        }
        return res;
    }
    public void bfs(Set<Integer> set, Queue<int[]> queue, int[][] matrix){
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++){
                    int x = cur[0] + dirs[d][0];
                    int y = cur[1] + dirs[d][1];
                    if (x < 0 || x >= m || y < 0 || y >= n || set.contains(x*n + y)) continue;
                    if (matrix[x][y] >= matrix[cur[0]][cur[1]]){
                        set.add(x*n + y);
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
    
        public List<List<Integer>> pacificAtlantic2(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new ArrayList();
            m = matrix.length;
            n = matrix[0].length;
            Queue<int[]> pacific = new LinkedList();
            Queue<int[]> atlantic = new LinkedList();
            boolean[][] pVisited = new boolean[m][n];
            boolean[][] aVisited = new boolean[m][n];
            for (int i = 0; i < m; i++){
                if (!pVisited[i][0]){
                    pVisited[i][0] = true;
                    pacific.add(new int[]{i, 0});
                }
                if (!aVisited[i][n-1]){
                    aVisited[i][n-1] = true;
                    atlantic.add(new int[]{i, n-1});
                }
            }
            for (int j = 0; j < n; j++){
                if (!pVisited[0][j]){
                    pVisited[0][j] = true;
                    pacific.add(new int[]{0, j});
                }
                if (!aVisited[m-1][j]){
                    aVisited[m-1][j] = true;
                    atlantic.add(new int[]{m-1, j});
                }
            }
            bfs2(pVisited, pacific, matrix);
            bfs2(aVisited, atlantic, matrix);
            List<List<Integer>> res = new ArrayList();
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    if (pVisited[i][j] && aVisited[i][j]){
                        List<Integer> list = new ArrayList();
                        list.add(i);
                        list.add(j);
                        res.add(list);
                    }
                }
            }
            return res;
        }
        public void bfs2(boolean[][] visited, Queue<int[]> queue, int[][] matrix){
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            while (!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++){
                    int[] cur = queue.poll();
                    for (int d = 0; d < 4; d++){
                        int x = cur[0] + dirs[d][0];
                        int y = cur[1] + dirs[d][1];
                        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
                        if (matrix[x][y] >= matrix[cur[0]][cur[1]]){
                            visited[x][y] = true;
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
    
}
