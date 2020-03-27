import java.util.ArrayList;
import java.util.List;

//Time: O(n) Space: O(1)
public class LeetCode763 {
	public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList();
        int[] lastIdxMap = new int[26]; //record the last apperance for each char
        for (int i = 0; i < S.length(); i++){
            lastIdxMap[S.charAt(i)-'a'] = i;
        }
        int prev = 0, cur = 0;
        for (int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            cur = Math.max(cur, lastIdxMap[c-'a']);
            if (cur == i){
                res.add(i-prev+1);
                cur++;
                prev = cur;
            }
        }
        return res;
    }
}
