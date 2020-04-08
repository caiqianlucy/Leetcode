/* author@ Qian Cai
 * Title@ Reorder Data in Log Files
 * You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  
Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs. 
 It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  
The letter-logs are ordered lexicographically ignoring identifier, with the 
identifier used in case of ties.  The digit-logs should be put in their original
order.

Return the final order of the logs
 * Time: O(nlogn) Space: O(1)
 */
import java.util.Arrays;
public class LeetCode937 {
	public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2)->{
            String[] arr1 = log1.split(" ", 2);
            String[] arr2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(arr1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(arr2[1].charAt(0));
            if (!isDigit1 && !isDigit2){
                int cmp = arr1[1].compareTo(arr2[1]);
                if (cmp != 0) return cmp;
                return arr1[0].compareTo(arr2[0]);
            } else return isDigit1 ? (isDigit2 ? 0: 1):-1;
        });
        return logs;
    }
}
