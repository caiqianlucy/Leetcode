import java.util.*;

/*author@Qian Cai
 * Title@Find Lucky Integer in an Array
 * Time: O(n)
 * Given an array of integers arr, a lucky integer is an integer which has a frequency in the array equal to its value.

Return a lucky integer in the array. If there are multiple lucky integers return the largest of them. If there is no lucky integer return -1.
 */
public class LeetCode1394 {
	public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        for (int n: arr){
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        int res = -1;
        for (int key: map.keySet()){
            if (key == map.get(key)){
                if (key > res) res = key;
            }
        }
        return res;
    }
}
