/* author@ Qian Cai
 * Title@ Minimum Window Substring
 * 
 * 
 */
import java.util.*;
public class LeetCode76 {
	public String minWindow(String s, String t) {
        if (t == null || t.length() == 0) return "";
        Map<Character, Integer> tmap = new HashMap();
        Map<Character, Integer> smap = new HashMap();
        for (char c: t.toCharArray()){
            tmap.put(c, tmap.getOrDefault(c, 0)+1);
        }
        int size = tmap.size();
        //System.out.println(size);
        int formed = 0; // tmap[(char)i] == smap[(char)i];
        int[] ans = new int[]{-1, 0, 0}; //len, start, end
        int left = 0, right = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            smap.put(c, smap.getOrDefault(c, 0)+1);
            if (tmap.containsKey(c) && tmap.get(c).equals(smap.get(c))){
                formed++;
                //if (formed == size) System.out.println(tmap.get(c).intValue());
            }
            while (formed == size && left <= right){
                if (ans[0] == -1 || ans[0] > right-left + 1){
                    ans[0] = right-left +1;
                    ans[1] = left;
                    ans[2] = right;
                }
                char cl = s.charAt(left);
                smap.put(cl, smap.get(cl)-1);
                if (tmap.containsKey(cl) && tmap.get(cl) > smap.get(cl)) formed--;
                left++;
            }
            right++;
        }
        return ans[0] == -1? "": s.substring(ans[1], ans[2]+1);
    }
}
