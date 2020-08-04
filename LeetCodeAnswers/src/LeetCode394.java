/* author@ Qian Cai
 * Title@ Decode String
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is 
being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets
 are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits 
are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * 
 */
import java.util.*;
public class LeetCode394 {
	public String decodeString(String s) {
        Deque<Character> queue = new LinkedList();
        for (char c: s.toCharArray()) {
        	queue.add(c);
        }
		return helper(queue);
	}
	public String helper(Deque<Character> queue) {
		StringBuilder sb = new StringBuilder();
		int num = 0;
		while (!queue.isEmpty()) {
			char c = queue.poll();
			if (Character.isDigit(c)) {
				num = num*10 -c -'0';
			} else if (c == '[') {
				String sub = helper(queue);
				for (int i = 0; i < num; i++) sb.append(sub);
				num = 0;
			} else if (c == ']') {
				break;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}