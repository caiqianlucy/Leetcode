/* author@ Qian Cai
 * Title@ Shortest Distance to Target Color
 * You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return 
the shortest distance between the given index i and the target color c. If there is
 no solution return -1.
 * 
 */
import java.util.*;
public class LeetCode1182 {
	//time: O(n + m) space: O(n)
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        int[][] left = new int[n][3];
        int[][] right = new int[n][3];
        int one = -1, two = -1, three = -1;
        for (int i = 0; i < n; i++){
            if (colors[i] == 1) one = i;
            else if (colors[i] == 2) two = i;
            else three = i;
            left[i][0] = (one == -1 ? Integer.MAX_VALUE: i-one);
            left[i][1] = (two == -1 ? Integer.MAX_VALUE: i-two);
            left[i][2] = (three == -1 ? Integer.MAX_VALUE: i-three);
        }
        one = -1;
        two = -1;
        three = -1;
        for (int i = n-1; i >= 0; i--){
            if (colors[i] == 1) one = i;
            else if (colors[i] == 2) two = i;
            else three = i;
            right[i][0] = (one == -1 ? Integer.MAX_VALUE: one-i);
            right[i][1] = (two == -1 ? Integer.MAX_VALUE: two-i);
            right[i][2] = (three == -1 ? Integer.MAX_VALUE: three-i);
        }
        int[][] closest = new int[n][3];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 3; j++){
                closest[i][j] = Math.min(left[i][j], right[i][j]);
                if (closest[i][j] == Integer.MAX_VALUE) closest[i][j] = -1;
            }   
        }
        List<Integer> ans = new ArrayList();
        for (int[] query: queries){
            ans.add(closest[query[0]][query[1]-1]);
        }
        return ans;            
    }
}
