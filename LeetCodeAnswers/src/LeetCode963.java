/* author@ Qian Cai
 * Title@ Minimum Area Rectangle II
 * 
 * Solution 1: time: O(n^3) space: O(n);
 * Solution 2: time: O((n^2)*(logn)) space: O(n^2)
 * 
 */
import java.awt.Point;
import java.util.*;
public class LeetCode963 {
	class Solution1 {
	    public double minAreaFreeRect(int[][] points) {
	        int n = points.length;
	        Point[] A = new Point[n];
	        Set<Point> set = new HashSet();
	        for (int i = 0; i < n; i++){
	            A[i] = new Point(points[i][0], points[i][1]);
	            set.add(A[i]);
	        }
	        double res = Double.MAX_VALUE;
	        for (int i = 0; i < n; i++){
	            Point p1 = A[i];
	            for (int j = 0; j < n; j++){
	                if (j == i) continue;
	                Point p2 = A[j];
	                for (int k = 0; k < n; k++){
	                    if (k == i || k== j) continue;
	                    Point p3 = A[k];
	                    double len1 = p1.distance(p2);
	                    double len2 = p1.distance(p3);
	                    double dia = p2.distance(p3);
	                    //check if the triangle is 90 degree
	                    //System.out.println(len1*len1 + len2*len2);
	                    //System.out.println(equal);
	                    if (Math.abs(len1*len1 + len2*len2 -dia*dia) < 0.000001){
	                        Point p4 = new Point(p2.x + p3.x - p1.x, p2.y + p3.y -p1.y);
	                        if (set.contains(p4)){
	                            double area = len1*len2;
	                            res = Math.min(res, area);
	                        }
	                    }                  
	                }
	            }
	        }
	        return res == Double.MAX_VALUE ? 0: res;
	    }
	}
	class Solution2 {
	    /*
	    n^2 distance
	   time: O(n^2*logn)
	    */
	   
	    public double minAreaFreeRect(int[][] points) {
	        int n = points.length;
	        Point[] A = new Point[n];
	        for (int i = 0; i < n; i++){
	            A[i] = new Point(points[i][0], points[i][1]);
	        }
	        Map<Integer, Map<Point, List<Point>>> seen = new HashMap();
	        for (int i = 0; i < n; i++){
	            for (int j = i+1; j < n; j++){
	                Point sum = new Point(A[i].x + A[j].x, A[i].y + A[j].y); //twice of the center
	                int dis = (A[i].x -A[j].x)*(A[i].x -A[j].x) + (A[i].y -A[j].y)*(A[i].y -A[j].y); //dis^2
	                if (!seen.containsKey(dis)){
	                    seen.put(dis, new HashMap<Point, List<Point>>());
	                }
	                if (!seen.get(dis).containsKey(sum)){
	                    seen.get(dis).put(sum, new ArrayList());
	                }
	                seen.get(dis).get(sum).add(A[i]); //only need to add one point because the other point can be determined based on center and distance                
	            }
	        }
	        double ans = Double.MAX_VALUE;
	        for (Map<Point, List<Point>> map: seen.values()){
	            for (Point center: map.keySet()){
	                List<Point> cand = map.get(center);
	                int size = cand.size();
	                for (int i = 0; i < size-1; i++){
	                    for (int j = i+1; j < size; j++){
	                        Point P = cand.get(i);
	                        Point Q = cand.get(j);
	                        Point Q2 = new Point(center.x-Q.x, center.y-Q.y);
	                        double area = P.distance(Q) * P.distance(Q2);
	                        ans = Math.min(area, ans);
	                    }
	                }
	            }
	        }
	        return ans == Double.MAX_VALUE ? 0: ans;
	    }
	}
}
