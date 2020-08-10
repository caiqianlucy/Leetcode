/* author@ Qian Cai
 * Title@ Stamping the Sequence
 * You want to form a target string of lowercase letters.

At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.

On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.

For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)

If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.

For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
 * 
 * time: o(MN) space: O(MN)
 */
import java.util.*;
public class LeetCode936 {
	class Solution {
	    public int[] movesToStamp(String stamp, String target) {
	        int m = stamp.length(), n = target.length();
	        Queue<Integer> queue = new LinkedList();
	        boolean[] done = new boolean[n];
	        Stack<Integer> ans = new Stack();
	        List<Node> A = new ArrayList();
	        for (int i = 0; i <= n-m; i++){
	            Set<Integer> made = new HashSet();
	            Set<Integer> todo = new HashSet();
	            for (int j = 0; j < m; j++){
	                if (target.charAt(i+j) == stamp.charAt(j)) made.add(i+j);
	                else todo.add(i+j);
	            }
	            A.add(new Node(made, todo));
	            if (todo.isEmpty()){
	                ans.push(i);
	                for (int j = i; j < i + m; j++){
	                    if(!done[j]){
	                        queue.add(j);
	                        done[j] = true;
	                    }
	                }
	            }
	        }
	            
	        while (!queue.isEmpty()){
	                int i = queue.poll();
	                for (int j = Math.max(0, i-m+1); j <= Math.min(n-m, i); j++){
	                    if (A.get(j).todo.contains(i)){
	                        A.get(j).todo.remove(i);
	                        if (A.get(j).todo.isEmpty()){
	                            ans.push(j);
	                            for (int k: A.get(j).made){
	                                if (!done[k]){
	                                    queue.add(k);
	                                    done[k] = true;
	                                }
	                            }
	                        }
	                    }
	                }
	            if (check(done)){
	                int[] res = new int[ans.size()];
	                 int idx = 0;
	                while (!ans.isEmpty()) res[idx++] = ans.pop();
	                return res;
	            }
	        }
	        
	        return new int[]{};
	        
	        }
	    public boolean check(boolean[] done){
	        for (boolean b: done){
	            if (!b) return false;
	        }
	        return true;
	    }
	    
	    class Node{
	        Set<Integer> made, todo;
	        Node(Set<Integer> m, Set<Integer> t){
	            made = m;
	            todo = t;
	        }
	    }
	}
	//Solution 2: simulate stamp process, stamp backward
	class Solution2 {
	    public int[] movesToStamp(String stamp, String target) {
	        int m = stamp.length(), n = target.length();
	        char[] s = stamp.toCharArray();
	        char[] t = target.toCharArray();
	        int cnt = 0;
	        Set<Integer> set = new HashSet();
	        Stack<Integer> ans = new Stack();
	        while (cnt < n){
	            boolean done = false;
	            for (int i = 0; i < n- m +1; i++){
	                if (!set.contains(i) && canStamp(s, t, i)){
	                    set.add(i);
	                    done = true;
	                    int changedChar = doStamp(s, t, i);
	                    cnt += changedChar;
	                    if (changedChar > 0) ans.push(i);
	                }
	            }
	            if (!done) return new int[]{};
	        }
	        int[] res = new int[ans.size()];
	        int idx = 0;
	        while (!ans.isEmpty()) res[idx++] = ans.pop();
	        return res;
	    }
	    public boolean canStamp(char[] s, char[] t, int i){
	        for (int j = i; j <= i+s.length-1; j++){
	            if (t[j] != s[j-i] && t[j] != '?') return false;
	        }
	        return true;
	    }
	    public int doStamp(char[] s, char[] t, int i){
	        int changed = 0;
	        for (int j = i; j <= i + s.length -1; j++){
	            if (t[j] != '?'){
	                t[j] = '?';
	                changed++;
	            }
	        }
	        return changed;
	    }
	}
}
