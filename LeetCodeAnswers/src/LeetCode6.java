/*author@ Qian Cai
 * Title@ ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 *  of rows like this: (you may want to display this pattern in a fixed font 
 *  for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number
 of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 * 
 */
import java.util.List;
import java.util.ArrayList;
public class LeetCode6 {
	public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList();
        int row = 0;
        int dir = -1;
        for (int i = 0; i < Math.min(s.length(), numRows); i++){
            rows.add(new StringBuilder());
        }
        for (char c: s.toCharArray()){
            rows.get(row).append(c);
            if (row == 0 || row == numRows-1){
                dir *= -1;
               
            }
             row += dir;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb: rows) res.append(sb);
        return res.toString();
    }
}
