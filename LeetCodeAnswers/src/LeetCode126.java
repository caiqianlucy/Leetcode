/* author@Qian Cai
 * Title@ Word Ladder II
 * ============================================================================
 * time, space O(mn), m is the length of each word, n is the length of wordList
 * ============================================================================
 * 
 */
import java.util.*;
public class LeetCode126 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);
        if (!wordSet.contains(endWord)) return new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String s: wordList){
            for (int i = 0; i < s.length(); i++){
                String key = s.substring(0, i) + '*' + s.substring(i+1);
                if (!map.containsKey(key)) map.put(key, new ArrayList());
                map.get(key).add(s);
            }
        }
        List<List<String>> res = new ArrayList();
        Set<String> visited = new HashSet();
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(beginWord));
        visited.add(beginWord);
        while (!queue.isEmpty()){
            Set<String> nextLevel = new HashSet();
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Node cur = queue.poll();
                if (cur.s.equals(endWord)) res.add(cur.path);
                else bfs(cur, map, queue, visited, nextLevel);
            }
            visited.addAll(nextLevel);
        }
        return res;
    }
    public void bfs(Node cur, Map<String, List<String>> map, Queue<Node> queue, Set<String> visited, Set<String> nextLevel){
        for (int i = 0; i < cur.s.length(); i++){
            String key = cur.s.substring(0, i) + '*' + cur.s.substring(i+1);
            if (!map.containsKey(key)) continue;
            for (String next: map.get(key)){
                if (!next.equals(cur.s) && !visited.contains(next)){
                    Node nei = new Node(next);
                    nei.path = new ArrayList<String>(cur.path);
                    nei.path.add(nei.s);
                    queue.add(nei);
                    nextLevel.add(next);
                }
            }
        }
    }
        
    public class Node{
        String s;
        List<String> path;
        public Node(String s){
            this.s = s;
            path = new ArrayList();
            path.add(s);
        }
    }
}
