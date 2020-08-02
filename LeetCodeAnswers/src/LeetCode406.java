/* author@ Qian Cai
 * Title@ Queue Reconstruction by Height
 * 
 */
import java.util.*;
public class LeetCode406 {
	//time: O(n^2)
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
            }
        });
        List<int[]> res = new ArrayList();
        for (int[] p: people){
            res.add(p[1], p);
        }
        int[][] ans = new int[people.length][2];
        for (int i = 0; i < people.length; i++){
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;     
    }
}
