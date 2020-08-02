/* author@ Qian Cai
 * Title@ Brick Wall
 * 
 * 
 */
import java.util.*;
public class LeetCode554 {
	public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> count = new HashMap();
        for (List<Integer> list: wall){
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++){
                sum += list.get(i);
                count.put(sum, count.getOrDefault(sum, 0) + 1);
            }
        }
        int max_val = 0;
        for (int val: count.values()){
            max_val = Math.max(max_val, val);
        }
        return wall.size()-max_val;
    }
}
