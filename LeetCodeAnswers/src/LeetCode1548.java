/* author@ Qian Cai
 * Title@ The Most Similar Path in a Graph
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. 
 * Each city has a name consisting of exactly 3 upper-case English letters given in the string array names. 
 * Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming
 *  an undirected connected graph).

You will be given a string array targetPath. You should find a path in the graph of the same length and with
 the minimum edit distance to targetPath.

You need to return the order of the nodes in the path with the minimum edit distance, The path should be of 
the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]). 
time: O(mn^2) space: O(EV + mn)
 */
import java.util.*;
public class LeetCode1548 {
	class Solution {
	    List<List<Integer>> graph; //adjList
	    int m, n; //length of city, length of targetPath
	    int[][] nextIdx; //nextIdx[i][j] from city i at path idx j the next Idx of city it went to which has min edit distance
	    int[][] memo; //memo[i][j] minDistance from city i at idx j
	    String[] names;
	    String[] targetPath;
	    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
	        m = targetPath.length;
	        this.n = n;
	        this.names = names;
	        this.targetPath = targetPath;
	        nextIdx = new int[n][m];
	        memo = new int[n][m];
	        graph = new ArrayList();
	        for (int[] row: memo) Arrays.fill(row, -1);
	        for (int i = 0; i < n; i++) graph.add(new ArrayList());
	        for (int[] road: roads){
	            int city1 = road[0], city2 = road[1];
	            graph.get(city1).add(city2);
	            graph.get(city2).add(city1);
	        }
	        int start = 0, minEditDis= Integer.MAX_VALUE;
	        for (int i = 0; i < n; i++){
	            int dis = dfs(i, 0);
	            if (dis < minEditDis){
	                minEditDis = dis;
	                start = i;
	            }
	        }
	        List<Integer> ans = new ArrayList();
	        while (ans.size() < m){
	            ans.add(start);
	            start = nextIdx[start][ans.size()-1];
	        }
	        return ans;
	    }
	    public int dfs(int from, int idx){
	        if (memo[from][idx] != -1) return memo[from][idx];
	        int editDis = (names[from].equals(targetPath[idx]) ? 0:1);
	        if (idx == m-1) return editDis;
	        int minDis = Integer.MAX_VALUE;
	        for (int nei: graph.get(from)){
	            int temp = dfs(nei, idx+1);
	            if (temp < minDis){
	                minDis = temp;
	                nextIdx[from][idx] = nei;
	            }
	        }
	        editDis += minDis;
	        memo[from][idx] = editDis;
	        return editDis;
	    }
	}
}
