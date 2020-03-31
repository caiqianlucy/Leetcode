import java.util.*;
/*author@ Qian Cai
 * Title@ Web Crawler
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl. 

Return all urls obtained by your web crawler in any order.

Your crawler should:

Start from the page: startUrl
Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
Do not crawl the same link twice.
Explore only the links that are under the same hostname as startUrl.
As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified. For example, the urls http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and http://example.com/abc are not under the same hostname.

The HtmlParser interface is defined as such: 

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  public List<String> getUrls(String url);
}
Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.
 * 
 * BFS time, space: O(n), n is the number of vertex
 */
public class LeetCode1236 {
	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = new HashSet();
        Queue<String> queue = new LinkedList();
        String hostName = getHostName(startUrl);
        queue.offer(startUrl);
        visited.add(startUrl);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for (String next: htmlParser.getUrls(cur)){
                if (getHostName(next).equals(hostName) && !visited.contains(next)){
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        return new ArrayList<String>(visited);
    }
    private String getHostName(String s){
        String[] arr = s.split("/");
        return arr[2];
    }
    interface HtmlParser {
    	 public default List<String> getUrls(String url) {
    		 return new LinkedList();
    	 }
    }
}
