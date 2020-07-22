/* author@Qian Cai
 * Title@ Swap Adjacent in LR String
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", 
 * a move consists of either replacing one occurrence of "XL" with "LX", 
 * or replacing one occurrence of "RX" with "XR". Given the starting string 
 * start and the ending string end, return True if and only if there exists a 
 * sequence of moves to transform one string to the other.
 * 
 */
public class LeetCode777 {
	public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))) return false;
        int i = 0, j = 0;
        int n = start.length();
        while (i < n && j < n){
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;
            if (i == n || j == n) return i == n && j == n;
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            i++;
            j++;
        }
        return true;
    }
}
