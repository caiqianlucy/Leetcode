/* author@ Qian Cai
 * Title@ Rectangle Area II
 * We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] ,
 *  where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the 
 *  coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.  Since the answer may be too large, 
return it modulo 10^9 + 7.
 * 
 */
import java.util.*;
public class LeetCode850 {
	//line sweep time: O(n^2logn)
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int MOD = (int) Math.pow(10, 9)+7;
        int[][] events = new int[rectangles.length*2][];
        int t = 0;
        for (int[] rec: rectangles){
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }
        Arrays.sort(events, (a, b)->(a[0]-b[0]));
        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event: events){
            int y = event[0], type = event[1], x1 = event[2], x2 = event[3];
            long query = 0; //area width between last y and this y
            int cur = -1; 
            for (int[] xs: active){
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }
            ans += query*(y-cur_y);
            ans %= MOD;
            if (type == OPEN){
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b)->Integer.compare(a[0], b[0]));
                
            } else {
                for (int i = 0; i < active.size(); i++){
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2){
                        active.remove(i);
                        break;
                    }
                }
            }
            cur_y = y;
        }
        
        return (int) ans;
        
    }
  //segment tree O(nlogn)
    public int rectangleArea2(int[][] rectangles) {
        int OPEN = 1, CLOSE = -1;
        int MOD =(int)Math.pow(10, 9)  + 7;
        int[][] events = new int[rectangles.length*2][4];
        Set<Integer> Xvals = new HashSet();
        int t = 0;
        for (int[] rec: rectangles){
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
        }
        Arrays.sort(events, (a, b)->(a[0]-b[0]));
        Integer[] X = new Integer[Xvals.size()];
        int idx = 0;
        for (Integer x: Xvals){
            X[idx++] = x;
        }
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap(); //keep index of each x value in sorted array
        for (int i = 0; i < X.length; i++){
            Xi.put(X[i], i);
        }
        Node active = new Node(0, X.length-1, X);
        long ans = 0;
        long cur_x_sum = 0;
        long cur_y = events[0][0];
        
        for (int[] event: events){
            int y = event[0], type = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum*(y-cur_y);
            ans %= MOD;
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), type);
            cur_y = y;
        }
        return (int)ans;
    }
    class Node{
        int start, end;
        Integer[] X;
        Node left, right;
        int count; //count > 0 count this interval
        long total; //total width from start to end index
        public Node(int start, int end, Integer[] X){
            this.start = start;
            this.end = end;
            this.X = X;
            left = null;
            right = null;
            count = 0;
            total = 0;
        }
        public int getMid(){
            return start + (end-start)/2;
        }
        public Node getLeft(){
            if (left == null) left = new Node(start, getMid(), X);
            return left;
        }
        public Node getRight(){
            if (right == null) right = new Node(getMid(), end, X);
            return right;
        }
        public long update(int i, int j, int val){
            if (i >= j) return 0;
            if (start == i && end == j){
                count += val;
            } else{
                getLeft().update(i, Math.min(getMid(), j), val);
                getRight().update(Math.max(getMid(), i), j, val);
            }
            if (count > 0) total = X[end]-X[start];
            else total = getLeft().total + getRight().total;
            //System.out.println("i: " + i + " j: " + j + " total: " + total);
            
            return total;
        }
        
        
    }
}
