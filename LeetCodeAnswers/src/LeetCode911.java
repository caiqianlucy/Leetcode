/* author@ Qian Cai
 * Title@ Online Election
 * In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.
 * 
 */
import java.util.*;
public class LeetCode911 {
	class TopVotedCandidate {
	    List<Integer> list; //list[i] stores the leading person at times[i]
	    int[] times;
	    public TopVotedCandidate(int[] persons, int[] times) {
	        this.times = times;
	        list = new ArrayList();
	        Map<Integer, Integer> count = new HashMap();
	        int leader = -1, c = 0;
	        for (int i = 0; i < persons.length; i++){
	            int p = persons[i], t = times[i];
	            int ci = count.getOrDefault(p, 0) + 1;
	            count.put(p, ci);
	            if (ci >= c){
	                leader = p;              
	            }
	            list.add(leader);
	            c = Math.max(c, ci);
	        }
	    }
	    
	    public int q(int t) {
	        int lo = 0, hi = times.length-1;
	        while (lo <= hi){
	            int mi = lo + (hi-lo)/2;
	            if (times[mi] > t) hi = mi-1;
	            else lo = mi+1;
	        }
	        //System.out.println(t);
	       // System.out.println(hi);
	        return list.get(hi);
	    }
	}
}
