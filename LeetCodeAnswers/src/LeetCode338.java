/*author@ Qian Cai
 * Title@ Counting Bit
 * Given a non negative integer number num. For every numbers i in the
 *  range 0 ≤ i ≤ num calculate the number of 1's in their binary 
 *  representation and return them as an array.
 * 
 */
public class LeetCode338 {
	public int[] countBits(int num) {
        if (num == 0)  return new int[]{0};
        int[] res = new int[num+1];
        res[0] = 0;
        for (int i = 1; i <= num; i++){
            res[i] = res[i>>1] + (i&1);
        }
        return res;
    }
}
