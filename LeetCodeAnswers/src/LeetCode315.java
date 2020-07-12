/* author@ Qian Cai
 * Title@ Count of Smaller Numbers after self
 * You are given an integer array nums and you have to return a new counts array. 
 * The counts array has the property where counts[i] is the number of smaller elements to 
 * the right of nums[i].
 * Solution
 * BIT, time: O(nlogn)
 */
import java.util.*;
public class LeetCode315 {
	public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList();
        if (nums == null || nums.length == 0) return res;
        //get the rank for each number
        int[] copy = nums.clone();
        Arrays.sort(copy);
        Map<Integer, Integer> rank = new HashMap(); //key: number value: rank
        rank.put(copy[0], 1);
        int prev = copy[0];
        int cur_rank = 1;
        for (int i = 1; i < copy.length; i++){
            if (copy[i] != prev){
                rank.put(copy[i], ++cur_rank);
                prev = copy[i];
            }
        }
        //update the BIT from last index
        BIT bit = new BIT(cur_rank);
        for (int i = nums.length-1; i >= 0; i--){
            int r = rank.get(nums[i]);
            res.add(0, bit.query(r-1)); //find how many counts smaller than r
            bit.update(r);
        }
        return res;
    }
    class BIT{
        int[] arr;
        private BIT(int n){
            arr = new int[n+1];
        }
        private void update(int r){
         
            while (r < arr.length){
                arr[r]++;
                r += (r&-r);
            }
        }
        private int query(int r){

            int res = 0;
            while (r > 0){
                res += arr[r];
                r -= (r&-r);
            }
            return res;
        }
    }
}
