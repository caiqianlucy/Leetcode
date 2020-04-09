import java.util.ArrayList;
import java.util.List;
/* author@ Qian Cai
 * Title@ Partition Lables
 * A string S of lowercase letters is given. We want to partition this string 
 * into as many parts as possible so that each letter appears in at most one 
 * part, and return a list of integers representing the size of these parts.
 * 
 * Solution
 * We need a lastIdxMap array to keep the last occurrence of each char
 * and a prev index to anchor the start of each partition 
 * cur keeps the idx of the last idx of all the char iterate through
 * when i == cur, that is the end of one partition, update res and set prev to cur
 */
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
