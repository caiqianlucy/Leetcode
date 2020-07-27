/* author@ Qian Cai
 * Title@Student Attendance Record I
 * 
 */
public class LeetCode551 {
	public boolean checkRecord(String s) {
        int countA = 0;
        int countL = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'A') {
                countA++;
                countL = 0;
            }
            else if (s.charAt(i) == 'L'){
                countL++;
                if (countL > 2) return false;
            } else {
                countL = 0;
            }
        }
        return countA <= 1;
    }
}
