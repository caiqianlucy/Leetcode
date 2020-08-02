/* author@ Qian Cai
 * Title@ Positions of Large Groups
 * 
 */
import java.util.*;
public class LeetCode830 {
	public List<List<Integer>> largeGroupPositions(String S) {
        int i = 0;
        List<List<Integer>> res = new ArrayList();
        while (i < S.length()){
            int j = i;
            while (j < S.length() && S.charAt(j) == S.charAt(i)) j++;
            if (j-i >= 3){
                List<Integer> list = new ArrayList();
                list.add(i);
                list.add(j-1);
                res.add(list);
            }
            i = j;
        }
        return res;
    }
}
