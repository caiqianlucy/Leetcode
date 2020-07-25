/* author@ Qian Cai
 * Title@ Reconstruct Itinerary
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.
 * //Hierholzer's Algorithm Time o(ElogE), space O(V+E)
 */
import java.util.*;
public class LeetCode332 {
	public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> map = new HashMap();
        for (List<String> ticket: tickets){
            String from = ticket.get(0), to = ticket.get(1);
            if (!map.containsKey(from)) map.put(from, new LinkedList());
            map.get(from).add(to);
        }
        
        List<String> res = new LinkedList();
        dfs(map, res, "JFK");
        return res;
    }
    public void dfs(Map<String, List<String>> map, List<String> res, String from){
        
        if (map.containsKey(from)) {
            List<String> list = map.get(from);
            Collections.sort(list);
            while (!list.isEmpty()){
                String to = list.remove(0);
                //System.out.println("to city is " + to);
                dfs(map, res, to);
            }
        }
        
       map.remove(from);
        //System.out.println("from city is " + from);
        res.add(0, from); //postorder
    }
    //iterative traversal
    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            Map<String, List<String>> map = new HashMap();
    		for (List<String> ticket: tickets){
    			String from = ticket.get(0), to = ticket.get(1);
    			if (!map.containsKey(from)) map.put(from, new ArrayList());
    			map.get(from).add(to);
    		}
            for (List<String> values: map.values()){
                Collections.sort(values);
            }
    		List<String> res = new ArrayList();
    		Stack<String> stack = new Stack();
            stack.push("JFK");
            while (!stack.isEmpty()){
                String from = stack.peek();
                
                if (map.containsKey(from) && map.get(from).size() > 0){
                    stack.push(map.get(from).remove(0));
                } else {
                    res.add(0, stack.pop());     //post-order
                }
                
            }
    		return res;
    	}
    }
}
