/*author@ Qian Cai
 * Title@ Minimum Cost to Connect Sticks
 * You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y. 
 You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.
 * Time: nlogn, space(n)
 */
import java.util.PriorityQueue;
public class LeetCode1167 {
	public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int n: sticks){
            pq.add(n);
        }
        int res = 0;
        while (pq.size() > 1){
            int first = pq.poll();
            int second = pq.poll();
            int newOne = first+second;
            res += newOne;
            pq.add(newOne);
        }
        return res;
    }
}
