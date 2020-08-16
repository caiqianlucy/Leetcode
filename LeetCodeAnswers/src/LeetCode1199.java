/* author@ Qian Cai
 * Title@ Minimum Time to Build Block
 * 
 * 
 */
import java.util.*;
public class LeetCode1199 {
	//Huffman's algorithm time: O(nlogn) space: O(n)
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int block: blocks) pq.add(block);
        while (pq.size() > 1){
            int one = pq.poll(), two = pq.poll();
            pq.add(two + split);
        }
        return pq.poll();
    }
}
