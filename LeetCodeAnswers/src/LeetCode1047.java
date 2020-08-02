/* author@ Qian Cai
 * Title@ Remove All Adjacent Duplicates in String
 * time: O(n)
 */
public class LeetCode1047 {
	public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c: S.toCharArray()){
            if (sb.length() != 0 && sb.charAt(sb.length()-1) == c){
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
