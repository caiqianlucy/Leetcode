/*author@ Qian Cai
 * Point of Latice: https://leetcode.com/discuss/interview-question/396418/
 * Time: O(logN) Space: O(1)
 */
public class PointOfLattice {
    public static int[] solution(int ax, int ay, int bx, int by) {
    	int dx = bx-ax, dy = by-ay;
    	int nx = dy, ny = -dx;
    	int gcd = gcd(Math.abs(dx), Math.abs(dy));
    	nx /= gcd;
    	ny /= gcd;
    	return new int[] {bx+nx, by+ny};
    }
    public static int gcd(int a, int b) {
    	return b == 0 ? a:gcd(b, a%b);
    }
}
