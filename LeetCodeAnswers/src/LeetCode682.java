/* author@ Qian Cai
 * Title@ Baseball Game
 * You're now a baseball game point recorder.

Given a list of strings, each string can be one of the 4 following types:

Integer (one round's score): Directly represents the number of points you get in this round.
"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
Each round's operation is permanent and could have an impact on the round before and the round after.

You need to return the sum of the points you could get in all the rounds.
 * 
 */
import java.util.*;
public class LeetCode682 {
	public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack();
        for (String op: ops){
            if (op.equals("C")) stack.pop();
            else if (op.equals("D")) stack.push(stack.peek() * 2);
            else if (op.equals("+")){
                int last = stack.pop();
                int cur = last + stack.peek();
                stack.push(last);
                stack.push(cur);
            } else {
                stack.push(Integer.valueOf(op));
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }
}
