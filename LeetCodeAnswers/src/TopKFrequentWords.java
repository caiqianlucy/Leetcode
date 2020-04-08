/*author@Qian Cai
 * Amazon OA 2020 Top K Frequently Mentioned Keywords
 * Given a list of reviews, a list of keywords and an integer k.
 *  Find the most popular k keywords in order of most to least 
 *  frequently mentioned. The comparison of strings is 
 *  case-insensitive. If keywords are mentioned an equal 
 *  number of times in reviews, sort alphabetically.
 *  Time: O(nlogk) Space: O(k)
 */
import java.util.*;

public class TopKFrequentWords {
    public static void main(String[] args) {
    	int k1 = 2;
    	String[] keywords1 = { "anacell", "cetracular", "betacellular" };
    	String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
    			"Best services provided by anacell, everyone should use anacell", };
    	int k2 = 2;
    	String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
    	String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
    			"betacellular has great services", "deltacellular provides much better services than betacellular",
    			"cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
    	System.out.println(solve(k1, keywords1, reviews1));
    	System.out.println(solve(k2, keywords2, reviews2));
    }
    public static List<String> solve(int k, String[] keywords, String[] reviews){
    	 List<String> res = new ArrayList();
    	 Set<String> keywordSet = new HashSet(Arrays.asList(keywords));
    	 Map<String, Integer> count = new HashMap();
    	 for (String r: reviews) {
    		 String[] words = r.split("\\W");
    		 Set<String> added = new HashSet();
    		 for (String s: words){
    			 s = s.toLowerCase();
    			 if (keywordSet.contains(s) && !added.contains(s)){
    				 count.put(s, count.getOrDefault(s, 0)+1);
    		     }	
    			 //System.out.println(s + " " + count.get(s));
    			 added.add(s);
    		 }
    	 }
    	 PriorityQueue<String> pq = new PriorityQueue<String>((a, b)->
    	 (count.get(a) == count.get(b) ? b.compareTo(a):count.get(a)-count.get(b)));
    	 for (String key: count.keySet()) {
    		 pq.offer(key);
    		 if (pq.size() > k) pq.poll();
    	 }
    	 while (!pq.isEmpty()) res.add(pq.poll());
    	 Collections.reverse(res);
    	 return res;
    }
}
