/*author@ Qian Cai
 * Title@ Circle and Rectangle Overlapping
 * Given a circle represented as (radius, x_center, y_center) and an axis-aligned rectangle represented as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the rectangle.

Return True if the circle and rectangle are overlapped otherwise return False.

In other words, check if there are any point (xi, yi) such that belongs to the circle and the rectangle at the same time.
 * Time, Space: O(1)
 */
public class LeetCode1401 {
	public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
	       int nearestX = Math.max(x1, Math.min(x2, x_center));
	       int nearestY = Math.max(y1, Math.min(y2, y_center));
	        //System.out.println(nearestY);
	       int distance = (nearestX-x_center)*(nearestX-x_center) + (nearestY-y_center)*(nearestY-y_center);
	        //System.out.println(distance);
	        return distance <= radius*radius;
	    }
}
