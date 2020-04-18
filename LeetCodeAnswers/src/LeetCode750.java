/* author@ Qian Cai
 * Title@ Number Of Corner Rectangles
 * Given a grid where each entry is only 0 or 1, find the number of corner 
 * rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned 
rectangle. Note that only the corners need to have the value 1. Also, all
 four 1s used must be distinct.
 * Time: O(mnMath.min(m,n))
 */
import java.util.*;
public class LeetCode750 {
	public int countCornerRectangles(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row: grid){
            for (int c1 = 0; c1 < c-1; c1++){
                if (row[c1] == 1){
                    for (int c2 = c1+1; c2 < c; c2++){
                        if (row[c2] == 1){
                            int pos = c1*200 + c2;
                            int n = count.getOrDefault(pos, 0);
                            ans += n;
                            count.put(pos,  n+1);
                        }
                    }
                } 
            }
        }
        return ans;
    }
}
