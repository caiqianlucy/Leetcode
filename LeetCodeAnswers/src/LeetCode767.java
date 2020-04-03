/*author@ Qian Cai
 * Title@ Reorganize String
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.
 * Time: O(n) Space: O(1)
 */
public class LeetCode767 {
	public String reorganizeString(String S) {
        int[] map = new int[26];
        for (char c: S.toCharArray()){
            map[c-'a']++;
        }
        int maxCount = 0, letter = 0;
        for (int i = 0; i < 26; i++){
            if (map[i] > maxCount){
                maxCount = map[i];
                letter = i;
            }
        }
        if (maxCount > (S.length()+1)/2) return "";
        char[] res = new char[S.length()];
        int idx = 0;
        while (map[letter] > 0){
            res[idx] = (char)(letter+'a');
            idx += 2;
            map[letter]--;
        }
        for (int i = 0; i < 26; i++){
            while (map[i] > 0){
                if (idx >= res.length){
                    idx = 1;
                }
                res[idx] = (char)(i+'a');
                map[i]--;
                idx += 2;
            }
        }
        return String.valueOf(res);
    }
}
