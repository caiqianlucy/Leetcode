/* author@ Qian Cai
 * Title@ Custom Sort String
 * time: O(n) space: O(1)
 */
public class LeetCode791 {
	public String customSortString(String S, String T) {
        int[] tCount = new int[26];
        for (int i = 0; i < T.length(); i++){
            char c = T.charAt(i);
            tCount[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c: S.toCharArray()){
            if (tCount[c-'a'] > 0){
                for (int i = 0; i < tCount[c-'a']; i++) sb.append(c);
                tCount[c-'a'] = 0;
            }
        }
        for (int i = 0; i < 26; i++){
            if (tCount[i] > 0){
                for (int j = 0; j < tCount[i]; j++){
                    sb.append((char)('a' + i));
                }
                tCount[i] = 0;
            }
        }
        return sb.toString();
    }
}
