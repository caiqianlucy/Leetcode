/* author@ Qian Cai
 * Title@ Distant Barcodes
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 * Time: O(n) Space: O(m) m distinct barcode number, n is length of barcodes
 */
import java.util.*;
public class LeetCode1054 {
	public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap(); //record count for each barcode
        for (int b: barcodes){
            map.put(b, map.getOrDefault(b, 0)+1);
        }
        int maxCount = 0, maxId = 0;
        for (int key: map.keySet()){
            if (map.get(key) > maxCount){
                maxCount = map.get(key);
                maxId = key;
            }
        }
        int i = 0;
        int n = barcodes.length;
        int[] res = new int[n];
        while (maxCount-- > 0){
            res[i] = maxId;
            i += 2;
        }
        map.remove(maxId);
        for (int key: map.keySet()){
            int count = map.get(key);
            while ( count-- > 0){
                if (i >= n){
                    i = 1;
                }
                res[i] = key;
                i += 2;
            }
        }
        return res;
    }
}
