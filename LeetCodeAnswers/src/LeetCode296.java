/* author@ Qian Cai
 * Title@ Best Meeting Point
 * A group of two or more people wants to meet and minimize the total travel distance. 
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone 
 * in the group. The distance is calculated using Manhattan Distance, 
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 */
import java.util.*;
public class LeetCode296 {
	//collect rows and cols value in O(mn) time, then convert one dimention problem
    public int minTotalDistance(int[][] grid) {
        List<Integer> xs = getX(grid);
        List<Integer> ys = getY(grid);
        int x = xs.get(xs.size()/2);
        int y = ys.get(ys.size()/2);
        return minDistance(xs, x) + minDistance(ys, y);
    }
    private int minDistance(List<Integer> list, int center){
        int res = 0;
        for (int from: list){
            res += Math.abs(from-center);
        }
        return res;
    }
    private List<Integer> getX(int[][] grid){
        List<Integer> res = new ArrayList();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1) res.add(i);
            }
        }
        return res;
    }
    private List<Integer> getY(int[][] grid){
        List<Integer> res = new ArrayList();
        for (int j = 0; j < grid[0].length; j++){
            for (int i = 0; i < grid.length; i++){
                if (grid[i][j] == 1) res.add(j);
            }
        }
        return res;
    }
}
