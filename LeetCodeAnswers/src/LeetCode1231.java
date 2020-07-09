/* author@ Qian Cai
 * Title@ Divide Chocolate
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

 
 * 
 */
public class LeetCode1231 {
	public int maximizeSweetness(int[] sweetness, int K) {
        int sum = 0;
		for (int s: sweetness) sum += s;
		int lo = 0, hi = sum/(K+1);
		while (lo <= hi){
			int mi = lo + (hi-lo)/2;
			if (!canDivide(sweetness, mi, K)) hi = mi-1;
			else lo = mi+1;
            }
		return hi;
        
    }
    public boolean canDivide(int[] sweetness, int t, int K){
		int count = 0;
		int cur = 0;
		for (int s: sweetness){
			cur += s;
			if (cur >= t){
				count++;
				cur = 0;
			}
		}
		return count >= K+1;

    }
}
