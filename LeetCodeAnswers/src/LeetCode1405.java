import java.util.PriorityQueue;

/*author@Qian Cai
 * Title@ Longest Happy String
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".
*Time O(n), Space: O(1)
 */
public class LeetCode1405 {
	 public String longestDiverseString(int a, int b, int c) {
	       StringBuilder sb = new StringBuilder();
	        PriorityQueue<Pair> pq = new PriorityQueue();
	        if (a > 0) pq.add(new Pair('a', a));
	        if (b > 0) pq.add(new Pair('b', b));
	        if (c > 0) pq.add(new Pair('c', c));

	        while (pq.size() > 1){
	            Pair one = pq.poll();
	            if (one.cnt >= 2){
	                append(one, 2, sb);
	            } else {
	                append(one, 1, sb);
	            }
	            Pair two = pq.poll();
	            if (two.cnt >= 2 && one.cnt <= two.cnt){
	                append(two, 2, sb);
	            }  else {
	                append(two, 1, sb);
	            }
	            if (one.cnt > 0) pq.add(one);
	            if (two.cnt > 0) pq.add(two);  
	        }
	        //check whether the remaining character and the last character is the same
	        if (!pq.isEmpty()){
	            if (sb.charAt(sb.length()-1) != pq.peek().ch){
	                Pair cur = pq.poll();
	                if (cur.cnt >= 2){
	                    append(cur, 2, sb);
	                } else {
	                    append(cur, 1, sb);
	                }
	            }
	        }
	        return sb.toString();
	                                                        
	        
	    }
	    public void append(Pair cur, int n, StringBuilder sb){
	        for (int i = 0; i < n; i++){
	            sb.append(cur.ch);
	        }
	        cur.cnt -= n;
	    }
	    //Pair class
	    public class Pair implements Comparable<Pair>{
	        char ch;
	        int cnt;
	        public Pair(char cha, int count){
	            ch = cha;
	            cnt = count;
	        }
	        //overide
	        public int compareTo(Pair b){
	            return this.cnt == b.cnt ? b.ch-this.ch : b.cnt-this.cnt;
	        }
	    }
}
