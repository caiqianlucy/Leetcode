/* author @ Qian Cai
 * Title@ Water and Jug Problem
 * time: O(log(Math.abs(x,y))
 * 
 */
public class LeetCode365 {
	public boolean canMeasureWater(int x, int y, int z) {
        int gcd = helper(x, y);
        if (gcd == 0) return z == 0;
        return z <= x+y && z % gcd == 0;
    }
    public int helper(int x, int y){
        if (y == 0) return x;
        return helper(y, x%y);
    }
}
