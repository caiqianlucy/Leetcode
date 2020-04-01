/*author@Qian Cai
 * Two Methods:
 * 1. bit manipulation+HashMap
 * 2. String + HashMap
 * Time, Space: o(1)
 * 
 */
import java.util.*;

public class LeetCode957 {
	public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap();
        int cur = convertToInt(cells);
        while (N > 0){
            if (map.containsKey(cur)){
                N %= map.get(cur) - N;
            }
            map.put(cur, N);
            if (N >= 1){
                N--;
                cur = next(cur);
            }
        }
        return convertBack(cur);
    }
    public int next(int n){
        int ans = 0;
        for (int i = 1; i <= 6; i++){
            if (((n >> (i-1)) & 1) == ((n >> (i+1)) & 1)){
                ans ^= 1 << i;
            }
        }
        return ans;
    }    
    public int[] convertBack(int n){
        int[] res = new int[8];
        for (int i = 0; i < 8; i++){
            res[i] = (n >> i) & 1;
        }
        return res;
    }      
    public int convertToInt(int[] cells){
        int res = 0;
        for (int i = 0; i < 8; i++){
            if (cells[i] == 1){
                res ^= 1 << i;
            }
        }
        return res;
    }
    public int[] prisonAfterNDays2(int[] cells, int N) {
        Map<String, Integer> map = new HashMap();
        while (N > 0){
            int[] next = new int[8];
            map.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; i++){
                next[i] = cells[i-1] == cells[i+1] ? 1:0;
            }
            cells = next;
            if (map.containsKey(Arrays.toString(cells))){
                N %= (map.get(Arrays.toString(cells)) - N);
            }
        }
        return cells;
    }
}
